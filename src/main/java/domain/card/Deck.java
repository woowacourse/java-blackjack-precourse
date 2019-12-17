package domain.card;

import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> deck;

    private Deck() {
        deck = CardFactory.create();
        Collections.shuffle(deck);
    }

    public static Deck init() {
        return new Deck();
    }

    public Card drawCard() {
        Card card = deck.get(0);
        deck.remove(card);
        return card;
    }
}
