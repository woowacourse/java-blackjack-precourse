package com.woowacourse.blackjack.domain.card;

import java.util.Objects;

/**
 * 카드 한장을 의미하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-16
 */
public class Card {
    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = Objects.requireNonNull(symbol);
        this.type = Objects.requireNonNull(type);
    }

    public boolean isSameSymbol(Symbol symbol) {
        return this.symbol.equals(symbol);
    }

    public int getScore() {
        return symbol.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return symbol == card.symbol && type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, type);
    }

    @Override
    public String toString() {
        return String.format("%s %s", symbol, type);
    }
}
