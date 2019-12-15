package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SingleDeck implements Deck {

    private List<Card> cards = new ArrayList<>();

    @Override
    public void shuffle() {
        cards = CardFactory.create();
        Collections.shuffle(cards);
    }

    @Override
    public Card get() {
        return null;
    }

    List<Card> getAll() {
        return cards;
    }
}
