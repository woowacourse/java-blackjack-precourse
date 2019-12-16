package domain.card;

import java.util.Objects;

/**
 * Card.java
 * 카드 한 장을 의미하는 객체.
 * 우아한테크코스 프리코스 3주차
 * Original code https://github.com/hotheadfactory/java-blackjack-precourse
 * Version: v0.0.1, 2019.12.16 (c) 정회형
 */
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

    public String toKorean() {
        return type.getAlias() + " " + symbol.getAlias();
    }

    public int getScore() {
        return symbol.getScore();
    }

    public boolean isAce() {
        return symbol.toString() == "ACE";
    }
}
