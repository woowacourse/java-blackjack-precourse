import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import utils.ConsoleOutput;
import utils.UserInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        List<Card> newCards = new ArrayList<>();
        newCards.addAll(CardFactory.create());
        Collections.shuffle(newCards);
        drawStartCards(newCards, START_DRAW);
        if(!checkBlackJack()) {
            startTurn(newCards);
        }
        printGameResult();
        findWinner();
    }


    private void drawStartCards(List<Card> newCards, int iteration) {
        for (int i = 0; i < iteration; i++) {
            drawEachOneCard(newCards);
        }
        System.out.println("딜러와 " + getUsersTest() + "에게 " + START_DRAW + "장의 카드를 나누었습니다.");
        printDealerCard();
        printPlayerCards();
    }

    private void printDealerCard() {
        ConsoleOutput.printCards(dealer.getFirstCardString());
    }

    private void printGameResult() {
        ConsoleOutput.printCards(dealer.getFinalCardString());
        players.forEach(x-> ConsoleOutput.printCards(x.getFinalCardString()));
    }

    private void findWinner() {
        // 딜러보다 낮으면 패배 높으면 승
        List<MoneyDTO> playerMoney = new ArrayList<>();
        MoneyDTO dealerMoney = new MoneyDTO("dealer", 0);
        players.stream().forEach(x -> playerMoney.add(compareScore(x, dealer, dealerMoney)));
        printMessage("## 최종 수익");
        printMessage("딜러: "+(int)dealerMoney.getMoney());
        playerMoney.forEach(x->printMessage(x.getName()+": "+x.getMoney()));
    }

    private MoneyDTO compareScore(Player player, Dealer dealer, MoneyDTO dealerMoney) {
        int profit = getProfit(player, dealer);
        dealerMoney.setMoney(dealerMoney.getMoney()+(-1*profit));
        return new MoneyDTO(player.getName(), profit);
    }

    private int getProfit(Player player, Dealer dealer) {
        if(!player.isBusted() && (player.getScore() > dealer.getScore() || dealer.isBusted())) {
            return (int)player.getBettingMoney();
        }
        if(player.getScore() < dealer.getScore() || player.isBusted()) {
            return (int)(-1*player.getBettingMoney());
        }
        if(player.isBlackJack() && !dealer.isBlackJack()) {
            return (int)(1.5* player.getBettingMoney());
        }
        return 0;
    }

    private void printPlayerCards() {
        players.forEach(x-> ConsoleOutput.printCards(x.getCardString()));
    }


    private String getUsersTest() {
        List<String> usersName = new ArrayList<>();
        players.stream().forEach(x -> usersName.add(x.getName()));
        return String.join(", ", usersName);
    }

    private void drawEachOneCard(List<Card> newCards) {
        dealer.addCard(drawTopCard(newCards));
        players.stream().forEach(x ->
                x.addCard(drawTopCard(newCards)));
    }

    private boolean checkBlackJack() {
        if(dealer.isBlackJack()) {
            printMessage("딜러가 블랙잭입니다.");
            return true;
        }
        if(players.stream().filter(x -> x.isBlackJack()).count() != 0) {
            printMessage("플레이어가 블랙잭입니다.");
            return true;
        }
        return false;
    }

    private void startTurn(List<Card> newCards) {
        players.stream().forEach(x -> askToDraw(x, newCards));
        while (dealer.isBelowRedraw()) {
            printMessage("딜러는 16이하라 한 장의 카드를 더 받았습니다.\n");
            dealer.addCard(drawTopCard(newCards));
        }
    }

    private Card drawTopCard(List<Card> newCards) {
        return newCards.remove(newCards.size() - 1);
    }

    private void askToDraw(Player player, List<Card> newCards) {
        String message = player.isHit();
        boolean continueDraw = true;
        while(continueDraw && !player.isBusted()) {
            printMessage(message);
            continueDraw = drawOneMore(player, newCards);
            printMessage(player.getCardString());
        }
        if(player.isBusted()) {
            printMessage("\n버스트! 배팅 금액을 모두 잃습니다.\n");
        }
    }

    private boolean drawOneMore(Player player, List<Card> newCards) {
        try {
            return askHit(player, newCards);
        }catch(IllegalArgumentException e) {
            printMessage(e.getMessage());
            drawOneMore(player, newCards);
        }
        return false;
    }

    private boolean askHit(Player player, List<Card> newCards) {
        if(UserInput.inputYesNo()) {
            player.addCard(drawTopCard(newCards));
            return true;
        }
        return false;
    }
}
