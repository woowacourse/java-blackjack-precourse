/*
 * @(#)Card.java        0.2 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.card;

import java.util.Objects;

/**
 * 카드 한장을 의미하는 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.2 2019.12.15
 */
public class Card {
    private final Symbol symbol;

    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    // TODO Card 관련 추가 기능 구현

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return symbol == card.symbol &&
                type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, type);
    }

    /**
     * 카드의 symbol의 숫자와 type을 문자열로 만들어 반환하는 메소드.
     * 기존의 코드에서 해당 프로그램에 사용하기 편하도록 리펙토링.
     *
     * @return 카드의 symbol의 숫자와 type을 합친 문자열.
     */
    @Override
    public String toString() {
        return symbol.getScore() +
                " " + type;
    }
}
