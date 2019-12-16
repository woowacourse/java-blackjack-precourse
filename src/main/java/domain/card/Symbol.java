/*
 * @(#)Symbol.java      0.3 2019.12.16
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.card;

/**
 * card의 번호를 열거형으로 저장한 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.3 2019.12.16
 */
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

    /**
     * Symbol의 숫자 값을 가지는 변수.
     */
    private int score;

    /**
     * Symbol의 이니셜을 가지는 변수.
     */
    private String initial;

    /**
     * Symbol을 생성하는 생성자.
     *
     * @param score   숫자 값.
     * @param initial 이니셜.
     */
    Symbol(int score, String initial) {
        this.score = score;
        this.initial = initial;
    }

    /**
     * 해당 symbol의 숫자를 반환하는 getter.
     *
     * @return symbol의 숫자를 반환.
     */
    public int getScore() {
        return score;
    }

    /**
     * 해당 symbol의 이니셜을 반환하는 getter.
     *
     * @return symbol의 이니셜을 반환.
     */
    public String getInitial() {
        return initial;
    }
}
