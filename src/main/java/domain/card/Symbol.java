/*
 * @(#)Symbol.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain.card;

public enum Symbol {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private int score;
    private String patten;

    Symbol(int score, String patten) {
        this.score = score;
        this.patten = patten;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return patten;
    }
}
