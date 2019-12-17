/*
 * @(#)SingleDeck.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain.card;

import common.BlackjackConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SingleDeck implements Deck {

    private List<Card> cards = new ArrayList<>();

    @Override
    public void shuffle() {
        cards = CardFactory.create();
        Collections.shuffle(cards);
    }

    @Override
    public Card pick() {
        if (isBlank()) {
            shuffle();
        }

        return cards.remove(BlackjackConfig.PICK_INDEX);
    }

    private boolean isBlank() {
        return cards.size() == 0;
    }

    List<Card> getAll() {
        return cards;
    }
}
