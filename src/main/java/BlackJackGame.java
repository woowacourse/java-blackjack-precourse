import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import view.ConsoleOutput;
import view.MoneyDTO;
import view.UserInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static view.ConsoleOutput.printMessage;

/**
 * BlackJackGame.java
 * 본 게임이 실행되는 Controller 클래스
 * 우아한테크코스 프리코스 3주차
 * Original code https://github.com/hotheadfactory/java-blackjack-precourse
 * Version: v0.0.1, 2019.12.16 (c) 정회형
 */

public class BlackJackGame {
    private final int START_DRAW = 2;
    private final List<Player> players;
    private final Dealer dealer;

    public BlackJackGame(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        startGame();
    }

    private void startGame() {
        List<Card> newCards = shuffleCards();
        drawStartCards(newCards);
        if (!dealer.isBlackJack()) {
            startTurn(newCards);
        }
        announceDetailedResult();
        announceWinner();
    }

    private List<Card> shuffleCards() {
        List<Card> newCards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(newCards);
        return newCards;
    }

    private String getPlayerNames() {
        List<String> playerNames = new ArrayList<>();
        players.forEach(x -> playerNames.add(x.getName()));
        return String.join(", ", playerNames);
    }

    private void drawStartCards(List<Card> newCards) {
        for (int i = 0; i < START_DRAW; i++) {
            drawEachOneCard(newCards);
        }
        ConsoleOutput.printFirstDraw(getPlayerNames(), START_DRAW);
        ConsoleOutput.printCards(dealer.getCardString());
        players.forEach(x -> ConsoleOutput.printCards(x.getCardString()));
    }

    private void drawEachOneCard(List<Card> newCards) {
        dealer.addCard(drawTopCard(newCards));
        players.forEach(x ->
                x.addCard(drawTopCard(newCards)));
    }

    private Card drawTopCard(List<Card> newCards) {
        return newCards.remove(newCards.size() - 1);
    }

    private void startTurn(List<Card> newCards) {
        players.forEach(x -> checkAdditionalDraw(x, newCards));
        while (dealer.isBelowRedraw()) {
            ConsoleOutput.printDealerRedraw();
            dealer.addCard(drawTopCard(newCards));
        }
    }

    private boolean checkAdditionalDraw(Player player, List<Card> newCards) {
        printMessage("\n" + player.getCardString());
        if (player.isBlackJack()) {
            ConsoleOutput.printBlackJack();
            return true;
        }
        askToDraw(player, newCards);
        return true;
    }

    private void askToDraw(Player player, List<Card> newCards) {
        drawContinuously(player, newCards);
        if (player.isBusted()) {
            ConsoleOutput.printBusted();
        }
    }

    private void drawContinuously(Player player, List<Card> newCards) {
        boolean continueDraw = true;
        while (continueDraw && !player.isBusted()) {
            printMessage(player.isHit());
            continueDraw = drawOneMore(player, newCards);
            printMessage(player.getCardString());
        }
    }

    private boolean drawOneMore(Player player, List<Card> newCards) {
        try {
            return askToHit(player, newCards);
        } catch (IllegalArgumentException e) {
            printMessage(e.getMessage());
            drawOneMore(player, newCards);
        }
        return false;
    }

    private boolean askToHit(Player player, List<Card> newCards) {
        if (UserInput.inputYesNo()) {
            player.addCard(drawTopCard(newCards));
            return true;
        }
        return false;
    }

    private MoneyDTO putBettingMoney(Player player, Dealer dealer, MoneyDTO dealerMoney) {
        int profit = getShare(player, dealer);
        dealerMoney.setMoney(dealerMoney.getMoney() + (-1 * profit));
        return new MoneyDTO(player.getName(), profit);
    }

    private void announceWinner() {
        // 딜러보다 낮으면 패배 높으면 승
        List<MoneyDTO> playerMoney = new ArrayList<>();
        MoneyDTO dealerMoney = new MoneyDTO("dealer", 0);
        players.forEach(x -> playerMoney.add(putBettingMoney(x, dealer, dealerMoney)));
        ConsoleOutput.printWinner(playerMoney, dealerMoney);
    }

    private int getShare(Player player, Dealer dealer) {
        int x = getProfit(player, dealer);
        if (x != 0) return x;
        if ((!player.isBusted() && player.getScore() < dealer.getScore()) || (player.isBusted() && !dealer.isBusted())) {
            return (int) (-1 * player.getBettingMoney());
        }
        return 0;
    }

    private int getProfit(Player player, Dealer dealer) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return (int) (1.5 * player.getBettingMoney());
        }
        if (!player.isBusted() && (player.getScore() > dealer.getScore() || (dealer.isBusted() && !player.isBusted()))) {
            return (int) player.getBettingMoney();
        }
        return 0;
    }

    private void announceDetailedResult() {
        ConsoleOutput.printCards(dealer.getFinalCardString());
        players.forEach(x -> ConsoleOutput.printCards(x.getFinalCardString()));
    }
}
