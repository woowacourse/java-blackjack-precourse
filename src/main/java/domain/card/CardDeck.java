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

import java.util.List;

public class CardDeck {
    private List<Card> cards;

    public CardDeck() {
        cards = CardFactory.create();
    }

    public Card getRandomCard() {
        Card randomCard = cards.get(getRandomIndex());
        cards.remove(randomCard);
        return randomCard;
    }

    private int getRandomIndex() {
        int numberOfCards = cards.size();
        int randomIndex = (int) (Math.random() * numberOfCards);
        return randomIndex;
    }
}
