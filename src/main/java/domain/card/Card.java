package domain.card;

import java.util.Collections;
import java.util.Objects;
import java.util.List;

public class Card {
    private final Symbol symbol;

    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    public void cardShuffled() {
    	Collections.shuffle(CardFactory.create());
    }
    
    public Card pickOneCard(List<Card> cards) {
    	Card target = cards.get(cards.size()-1);
    	cards.remove(cards.size()-1);
    	return target;
    }
    
    public String showSymbol() {
    	return symbol.name();
    }
    
    public String showType() {
    	return type.name();
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
