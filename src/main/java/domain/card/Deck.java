package domain.card;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;
    private HashMap<Integer, Boolean> cardsOnTable = new HashMap<Integer, Boolean>();

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public Card giveRandomCard() {
        Random random = new Random();
        int IndexOfCard;
        do {
            IndexOfCard = random.nextInt(cards.size());
        } while (cardsOnTable.containsKey(IndexOfCard));
        cardsOnTable.put(IndexOfCard, true);
        return cards.get(IndexOfCard);
    }
}
