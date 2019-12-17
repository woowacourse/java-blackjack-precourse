/*
 * CardDeck.java
 *
 * version 1.0
 *
 * 20191216
 *
 * copyright by jiwonSong(s26788761@naver.com)
 *
 */

package domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private List<Card> cards;
    private List<Card> usedCards;

    public CardDeck() {
        cards = CardFactory.create();
        usedCards = new ArrayList<>();
    }

    public Card getRandomCard() {
        Card randomCard;

        do {
            randomCard = cards.get(getRandomIndex());
        } while (!isUsed(randomCard));

        usedCards.add(randomCard);
        return randomCard;
    }

    private boolean isUsed(Card card) {
        if (usedCards.contains(card)) {
            return true;
        }
        return false;
    }

    private int getRandomIndex() {
        int numberOfCards = cards.size();
        int randomIndex = (int) (Math.random() * numberOfCards);
        return randomIndex;
    }
}
