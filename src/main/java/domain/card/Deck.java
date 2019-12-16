package domain.card;

import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> deck;
    
    public Deck() {
        deck = CardFactory.create();
        Collections.shuffle(deck);
    }

    public Card draw() {
        return deck.remove(0);
    }
}