import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Person;
import domain.user.Player;
import utils.ConsoleOutput;
import utils.UserInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static utils.ConsoleOutput.printMessage;

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
        List<Card> newCards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(newCards);
        drawStartCards(newCards);
        if(!dealer.isBlackJack()) {
            startTurn(newCards);
        }
        printGameResult();
        findWinner();
    }


    private void drawStartCards(List<Card> newCards) {
        for (int i = 0; i < START_DRAW; i++) {
            drawEachOneCard(newCards);
        }
        ConsoleOutput.printFirstDraw(getPlayerNames(), START_DRAW);
        printDealerCard();
        printPlayerCards();
    }

    private void printDealerCard() {
        ConsoleOutput.printCards(dealer.getCardString());
    }

    private void printGameResult() {
        ConsoleOutput.printCards(dealer.getFinalCardString());
        players.forEach(x -> ConsoleOutput.printCards(x.getFinalCardString()));
    }

    private void findWinner() {
        // 딜러보다 낮으면 패배 높으면 승
        List<MoneyDTO> playerMoney = new ArrayList<>();
        MoneyDTO dealerMoney = new MoneyDTO("dealer", 0);
        players.forEach(x -> playerMoney.add(compareScore(x, dealer, dealerMoney)));
        printMessage("## 최종 수익");
        printMessage("딜러: " + dealerMoney.getMoney());
        playerMoney.forEach(x -> printMessage(x.getName() + ": " + x.getMoney()));
    }

    private MoneyDTO compareScore(Player player, Dealer dealer, MoneyDTO dealerMoney) {
        int profit = getProfit(player, dealer);
        dealerMoney.setMoney(dealerMoney.getMoney() + (-1 * profit));
        return new MoneyDTO(player.getName(), profit);
    }

    private int getProfit(Player player, Dealer dealer) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return (int) (1.5 * player.getBettingMoney());
        }
        if (!player.isBusted() && (player.getScore() > dealer.getScore() || (dealer.isBusted() && !player.isBusted()))) {
            return (int) player.getBettingMoney();
        }
        if (player.getScore() < dealer.getScore() || (player.isBusted() && !dealer.isBusted())) {
            return (int) (-1 * player.getBettingMoney());
        }
        return 0;
    }

    private void printPlayerCards() {
        players.forEach(x -> ConsoleOutput.printCards(x.getCardString()));
    }


    private String getPlayerNames() {
        List<String> playerNames = new ArrayList<>();
        players.forEach(x -> playerNames.add(x.getName()));
        return String.join(", ", playerNames);
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
        if(player.isBlackJack()) {
            printMessage("블랙잭! 150%의 배당을 받습니다.");
            return true;
        }
        askToDraw(player, newCards);
        return true;
    }

    private void askToDraw(Player player, List<Card> newCards) {
        boolean continueDraw = true;
        while (continueDraw && !player.isBusted()) {
            printMessage(player.isHit());
            continueDraw = drawOneMore(player, newCards);
            printMessage(player.getCardString());
        }
        if (player.isBusted()) {
            ConsoleOutput.printBusted();
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
}
