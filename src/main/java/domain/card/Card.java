package domain.card;

import java.util.Objects;

/**
 * 카드 한장을 의미하는 객체
 */
public class Card {
    private static final String OPEN_BRACE = "[";
    private static final String SPACE = " ";
    private static final String CLOSE_BRACE = "]";
    
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
        return OPEN_BRACE + symbol + SPACE + type + CLOSE_BRACE;
    }
    
    public Symbol getSymbol() {
        return this.symbol;
    }
    
    public Type getType() {
        return this.type;
    }
}
