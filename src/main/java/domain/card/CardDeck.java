package domain.card;

import java.util.*;

public class CardDeck {
    private Random random = new Random();
    private List<Card> cardDeck = new ArrayList<Card>(CardFactory.create());

    public Card drawACard() throws Exception {
        try {
            Card card = cardDeck.get(random.nextInt(cardDeck.size()));
            cardDeck.remove(card);
            return card;
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
