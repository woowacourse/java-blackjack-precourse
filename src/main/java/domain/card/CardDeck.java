package domain.card;

import java.util.*;

public class CardDeck {
    private static List<Card> cardDeck = new ArrayList<Card>();

    private CardDeck() {
        cardDeck = CardFactory.create();
    }

    public static Card drawACard() {
        Card card = new Card(Symbol.getRandom(), Type.getRandom());
        cardDeck.remove(card);
        return card;
    }
}
