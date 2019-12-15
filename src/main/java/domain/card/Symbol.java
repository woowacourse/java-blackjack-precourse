/*
 * @(#)Symbol.java      0.1 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.card;

/**
 * 카드의 번호를 열거형으로 저장한 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.1 2019.12.15
 */
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
