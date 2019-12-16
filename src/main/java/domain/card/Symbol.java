/*
 * @(#)Symbol.java      0.2 2019.12.16
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.card;

/**
 * card의 번호를 열거형으로 저장한 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.2 2019.12.16
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

    /**
     * 해당 symbol의 숫자를 반환하는 getter.
     *
     * @return symbol의 숫자를 반환.
     */
    public int getScore() {
        return score;
    }
}
