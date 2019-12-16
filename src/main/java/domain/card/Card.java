package domain.card;

import domain.Game;

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
    public int getSymbolValue(){
        return symbol.getScore();
    }
    public int isAce(){
        if(symbol == Symbol.ACE){
            return 1;
        }
        return 0;
    }
    public static Symbol makeRandomSymbol() {
        int symbol = (int) (Math.random() * Game.NUM_OF_SYMBOL + 1);
        return Symbol.values()[symbol];
    }

    public static Type makeRandomType() {
        int type = (int) (Math.random() * Game.NUM_OF_TYPE);
        return Type.values()[type];
    }
}
