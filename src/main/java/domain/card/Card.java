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

    // TODO Card 관련 추가 기능 구현
    public String getCardName() {
        if(symbol.getWord() != "A" 
            && symbol.getWord() != "J" 
            && symbol.getWord() != "Q" 
            && symbol.getWord() != "K") {
            return symbol.getScore() + " " + type;
        }
        return symbol.getWord() + " " + type;
    }

    public int getCardScore() {
        return this.symbol.getScore();
    }

    public int isAce() {
        if(symbol.getWord() == "A") {
            return 1;
        }
        return 0;
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
