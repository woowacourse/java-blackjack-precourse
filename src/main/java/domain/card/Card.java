/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * Card.java
 * 카드 한장을 의미하는 객체
 *
 * @author      Sorin Jin
 * @version     1.0
 * @date        16 Dec 2019
 *
 */

package domain.card;

import java.util.Objects;

public class Card {
    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

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

    @Override
    public String toString() {
        return "Card{" +
                "symbol=" + symbol +
                ", type=" + type +
                '}';
    }
}
