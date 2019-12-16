package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardDivider {
    private final List<Card> cardList;
    private List<Card> usedCards = new ArrayList<Card>();

    public CardDivider(List<Card> cardList) {
        this.cardList = cardList;

    }

    private void cardListRandomShuffle(List<Card> cardList) {
        long seed = System.nanoTime();
        Collections.shuffle(cardList, new Random(seed));
    }

    public Card getOneCard() {

        if (cardList == null || cardList.size() < 1) {
            return null;
        }

        return cardList.get(0);
    }

    private boolean isAlreadyUsed(Card card) {
        boolean isUsed = false;

        for (int idx = 0; idx < usedCards.size() && !isUsed; idx++) {
            isUsed = usedCards.get(idx).equals(card);
        }

        return isUsed;
    }
}
