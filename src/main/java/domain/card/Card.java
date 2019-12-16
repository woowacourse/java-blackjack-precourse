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

    /**
     * 카드의 symbol과 type을 출력하는 함수
     * 할당된 alphabet이 있을 경우, alphabet을 출력한다.
     */
    public String printCard() {
        if (symbol.getAlphabet() != ' ') return symbol.getAlphabet() + type.toString();
        return symbol.getScore() + type.toString();
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public boolean isAce() {
        if (symbol.equals(Symbol.ACE)) return true;
        return false;
    }
}
