package domain.card;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
    private List<Card> deck = CardFactory.create();

    public Deck() {
    }

    public List<Card> getDeck() {
        return deck;
    }

    private void removeCardFromDeck(Card card) {
        deck.remove(card);
    }

    public Card drawCard() {
        Card drawnCard = ThreadLocalRandom.current().ints(0, deck.size())
                .limit(1)
                .mapToObj(x -> deck.get(x))
                .findAny()
                .orElseThrow(IllegalStateException::new);

        removeCardFromDeck(drawnCard);
        return drawnCard;
    }

}
