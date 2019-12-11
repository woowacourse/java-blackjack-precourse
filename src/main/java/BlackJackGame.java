import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.*;

public class BlackJackGame {
    private final int START_DRAW = 2;
    private final List<Player> players;
    private final Dealer dealer;

    public BlackJackGame(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        //cards.forEach(x -> System.out.println(x.toString()));
        startGame();
    }

    private void startGame() {
        Random random = new Random();
        List<Card> newCards = new ArrayList<>();
        newCards.addAll(CardFactory.create());
        Collections.shuffle(newCards);
        drawCards(newCards, START_DRAW);
    }

    private void drawCards(List<Card> newCards, int iteration) {
        for(int i = 0; i < iteration; i++) {
            drawOneCard(newCards);
        }
    }

    private void drawOneCard(List<Card> newCards) {
        System.out.println(newCards.size());
        this.addCard(newCards, dealer);
        players.stream().forEach(x ->
                this.addCard(newCards,x));
        System.out.println(newCards.size());
    }

    private void addCard(List<Card> newCards, Player player) {
        player.addCard(newCards.remove(newCards.size()-1));
    }

    private void addCard(List<Card> newCards, Dealer dealer) {
        dealer.addCard(newCards.remove(newCards.size()-1));
    }
}
