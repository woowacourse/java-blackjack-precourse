/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * Deck.java
 * 카드 묶음의 정보를 가지고 있는 클래스
 *
 * @author      Sorin Jin
 * @version     1.0
 * @date        16 Dec 2019
 *
 */

package domain.card;

import java.util.List;

public class Deck {
    private List<Card> cards;
    private int cardIndex;

    public Deck() {
        cards = CardFactory.create();
        cardIndex = 0;
    }

    public Card getCard() {
        return cards.get(cardIndex++);
    }
}
