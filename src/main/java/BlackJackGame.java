import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import utils.ConsoleOutput;
import utils.UserInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
        Random random = new Random();
        List<Card> newCards = new ArrayList<>();
        newCards.addAll(CardFactory.create());
        Collections.shuffle(newCards);
        drawStartCards(newCards, START_DRAW);
        getScoresTest();
        getBlackJackTest();
        startTurn(newCards);
    }


    private void drawStartCards(List<Card> newCards, int iteration) {
        for (int i = 0; i < iteration; i++) {
            drawEachOneCard(newCards);
        }
        System.out.println("딜러와 " + getUsersTest() + "에게 " + START_DRAW + "장의 카드를 나누었습니다.");
        printDealerCards();
        printPlayerCards();
    }

    private void printDealerCards() {
        ConsoleOutput.printCards(dealer.getCardString());
    }

    private void printPlayerCards() {
        players.forEach(x-> ConsoleOutput.printCards(x.getCardString()));
    }

    private void getScoresTest() {
        // Test
        players.stream().forEach(x -> System.out.println(x.getNameTest() + " 점수: " + x.getScoreTest()));
    }

    private void getBlackJackTest() {
        // Test
        System.out.println("딜러 블랙잭: " + dealer.isBlackJack());
        players.stream().forEach(x -> System.out.println(x.getNameTest() + " 블랙잭: " + x.isBlackJack()));
    }

    private String getUsersTest() {
        List<String> usersName = new ArrayList<>();
        players.stream().forEach(x -> usersName.add(x.getNameTest()));
        return String.join(", ", usersName);
    }

    private void drawEachOneCard(List<Card> newCards) {
        dealer.addCard(drawTopCard(newCards));
        players.stream().forEach(x ->
                x.addCard(drawTopCard(newCards)));
    }

    private void startTurn(List<Card> newCards) {
        while (true) {
            if (dealer.isBelowRedraw()) {
                printMessage("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
                dealer.addCard(drawTopCard(newCards));
            }
            players.stream().forEach(x -> askToDraw(x, newCards));
            printDealerCards();
            printPlayerCards();
            getScoresTest();
        }
    }

    private Card drawTopCard(List<Card> newCards) {
        return newCards.remove(newCards.size() - 1);
    }

    private void askToDraw(Player player, List<Card> newCards) {
        String message = player.isReadyToGo();
        if (!message.isEmpty()) {
            printMessage(message);
            drawOneMore(player, newCards);
        }
    }

    private void drawOneMore(Player player, List<Card> newCards) {
        try {
            if(UserInput.inputYesOrNo()) {
                player.addCard(drawTopCard(newCards));
            }
        }catch(IllegalArgumentException e) {
            printMessage(e.getMessage());
            drawOneMore(player, newCards);
        }

    }
}
