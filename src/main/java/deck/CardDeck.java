package deck;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class CardDeck {
    private ArrayList<Card> allCards;
    private boolean usedCard[];

    public CardDeck(List<Card> card) {
        allCards = new ArrayList<>(card);
        usedCard = new boolean[allCards.size() + 1];
    }

    public Card drawCard() {
        int idx = randomIndex();
        usedCardCheck(idx);
        return allCards.get(idx);
    }

    private int randomIndex() {
        int idx = 0;
        do {
            idx = (int) (Math.random() * allCards.size());
        } while (usedCard[idx] == true);
        return idx;
    }

    private void usedCardCheck(int idx) {
        usedCard[idx] = true;
    }

}