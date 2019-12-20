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
    
    public String getSymbol() {
    	if (this.symbol == Symbol.ACE) {
    		return "A";
    	} else if (this.symbol == Symbol.KING) {
    		return "K";
    	} else if (this.symbol == Symbol.QUEEN) {
    		return "Q";
    	} else if (this.symbol == Symbol.JACK) {
    		return "J";
    	}
    	return String.valueOf(this.symbol.getScore());
    }
    
    public String getType() {
    	return this.type.getType();
    }
    
    public int getCardScore() {
    	return this.symbol.getScore();
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
