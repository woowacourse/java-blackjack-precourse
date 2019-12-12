import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import utils.ConsoleOutput;

import java.util.*;

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
        // Test
        players.stream().forEach(x-> System.out.println("점수: "+x.getScoreTest()));

    }

    private void drawStartCards(List<Card> newCards, int iteration) {
        for (int i = 0; i < iteration; i++) {
            drawEachOneCard(newCards);
        }
        ConsoleOutput.printCardStatus(players,dealer);
    }

    private void drawEachOneCard(List<Card> newCards) {
        dealer.addCard(newCards.remove(newCards.size() - 1));
        players.stream().forEach(x ->
                x.addCard(newCards.remove(newCards.size() - 1)));
    }
}
