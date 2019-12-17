/*
 * @(#)Deck.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain.card;

public interface Deck {
    void shuffle();

    Card pick();
}
