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
    ACE(11),
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
