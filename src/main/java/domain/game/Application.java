package domain.game;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Card> cards = CardFactory.create();
        Blackjack game = new Blackjack(cards);
        game.start();
    }
}
