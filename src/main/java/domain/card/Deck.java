package domain.card;

import java.util.List;
import java.util.ListIterator;
import Exception.DeckHasNoCardException;

public class Deck {
    private List<Card> deck;
    private ListIterator<Card> deckIterator;

    public Deck() {
        this.deck = CardFactory.create();
        deckIterator = deck.listIterator();
    }

    public Card draw() throws DeckHasNoCardException {
        if (deckIterator.hasNext()) {
            return deckIterator.next();
        }
        throw new DeckHasNoCardException("덱에 남아있는 카드가 없습니다.");
    }
}