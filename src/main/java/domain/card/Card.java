package domain.card;

import java.util.Objects;

import static domain.card.Symbol.ACE;

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

    public int getScore() {
        return symbol.getScore();
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
        if (symbol == ACE) {
            return "[" + type + " " + symbol +
                    "(" + symbol.getScore() + " or 11)]";
        }
        return "[" + type + " " + symbol +
                "(" + symbol.getScore() + ")]";
    }
}
