package domain.card;

import java.util.Objects;

/**
 * 카드 한장을 의미하는 객체
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

    public Symbol getSymbol() {
        return this.symbol;
    }

    public Type getType() {
        return this.type;
    }

    public String toCardString() {
        if (this.type == Type.SPADE) {
            return this.symbol.getScore() + "스페이드";
        }
        if (this.type == Type.DIAMOND) {
            return this.symbol.getScore() + "다이아몬드";
        }
        if (this.type == Type.HEART) {
            return this.symbol.getScore() + "하트";
        }
        if (this.type == Type.CLUB) {
            return this.symbol.getScore() + "클로버";
        }
        return null;
    }

}
