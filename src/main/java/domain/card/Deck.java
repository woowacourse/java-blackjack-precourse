package domain.card;

import java.util.List;

public class Deck {
    private List<Card> deck;

    public Deck() {
        deck = CardFactory.create();
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void removeCardFromDeck(Card card) {
        deck.remove(card);
    }
}
