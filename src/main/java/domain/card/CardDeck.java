package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
