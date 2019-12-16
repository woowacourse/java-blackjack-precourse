/*
 * @(#)Card.java        0.5 2019.12.16
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.card;

import java.util.Objects;

/**
 * card 한장을 의미하는 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.5 2019.12.16
 */
public class Card {
    /**
     * card의 symbol을 저장하는 변수.
     */
    private final Symbol symbol;

    /**
     * card의 type을 저장하는 변수.
     */
    private final Type type;

    /**
     * card의 symbol과 type을 받아서 생성하는 Card 매개변수 생성자.
     *
     * @param symbol 생성할 카드의 symbol.
     * @param type   생성할 카드의 type.
     */
    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    /**
     * card 객체가 동일한 지(내용이 같은지)를 확인할 때 사용될 equals의 overriding 메소드.
     *
     * @param o 비교를 할 object.
     * @return 동일하면 true 반환.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return symbol == card.symbol &&
                type == card.type;
    }

    /**
     * card를 Hashmap등에 사용할 때 필요한 hashCode의 overriding 메소드.
     *
     * @return 같은 card이면 true.
     */
    @Override
    public int hashCode() {
        return Objects.hash(symbol, type);
    }

    /**
     * card의 symbol의 숫자와 type을 문자열로 만들어 반환하는 메소드.
     * 기존에 제공한 코드에서 해당 프로그램에 출력하여 사용하기 편하도록 리펙토링.
     *
     * @return card의 symbol의 숫자와 type을 합친 문자열.
     */
    @Override
    public String toString() {
        return symbol.getInitial() +
                " " + type;
    }

    /**
     * card의 symbol이 ACE인지 확인하는 메소드.
     *
     * @return card의 symbol이 ACE이면 true 반환.
     */
    public boolean isAceCard() {
        return (symbol == Symbol.ACE);
    }

    /**
     * card의 숫자(symbol의 score)를 반환하는 메소드.
     *
     * @return card의 symbol의 숫자.
     */
    public int getSymbolScore() {
        return symbol.getScore();
    }
}
