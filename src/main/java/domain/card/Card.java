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

    public int getScore() { // 점수를 반환하는 메소드
        return symbol.getScore();
    }

    public String getType() { // 카드의 종류를 한글로 반환
        String typeKor;
        if (type == Type.SPADE) {
            typeKor = "스페이드";
        } else if (type == Type.DIAMOND) {
            typeKor = "다이아몬드";
        } else if (type == Type.HEART) {
            typeKor = "하트";
        } else if (type == Type.CLUB) {
            typeKor = "클로버";
        } else {
            typeKor = "False";
        }
        return typeKor;
    }

}
