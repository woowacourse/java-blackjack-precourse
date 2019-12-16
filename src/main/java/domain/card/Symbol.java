/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * Symbol.java
 * 카드 symbol 데이터
 *
 * @author      Sorin Jin
 * @version     1.0
 * @date        16 Dec 2019
 *
 */

package domain.card;

public enum Symbol {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private int score;

    Symbol(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
