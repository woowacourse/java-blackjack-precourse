package domain.card;

import java.util.List;
import java.util.ListIterator;

public class Deck {
    private List<Card> deck;
    private ListIterator<Card> deckIterator;

    public Deck() {
        this.deck = CardFactory.create();
        deckIterator = deck.listIterator();
    }

    public Card draw() throws Exception {
        if (deckIterator.hasNext()) {
            return deckIterator.next();
        }
        throw new Exception("덱에 남아있는 카드가 없습니다.");
    }
}