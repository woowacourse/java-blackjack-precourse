/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.model.card;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote Card 객체에 사용될 Symbol enum입니다.
 * @since : 2019.12.17 화요일
 */
public enum Symbol {
    ACE(11, "A"),
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
    private String letter;

    Symbol(int score, String letter) {
        this.score = score;
        this.letter = letter;
    }

    public int getScore() {
        return score;
    }

    public String getLetter() {
        return letter;
    }
}
