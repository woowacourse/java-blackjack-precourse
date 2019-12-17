package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static List<Card> cards;

    public Deck() {
        List<Card> cards = new ArrayList<>();
        for (Card card : CardFactory.create()) {
            cards.add(card);
        }
        this.cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card pop() {
        return this.cards.remove(0);
    }

    public int getSize() {
        return this.cards.size();
    }
}
