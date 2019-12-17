/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.model.card;

import java.util.Objects;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 카드 한장을 의미하는 객체입니다.
 * @since : 2019.12.17 화요일
 */
public class Card {
    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) { // 두 객체의 내용이 같은지 확인하는 equals를 Card에 맞게 오버라이드.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return symbol == card.symbol &&
                type == card.type;
    }

    @Override
    public int hashCode() { // 두 객체가 같은 객체인이지 확인, hashCode도 같도록 만들기.
        return Objects.hash(symbol, type);

    }

    @Override
    public String toString() {
        return getSymbolScoreByLetter(symbol) + type.getType();
    }

    public int getSymbolScore() {
        return symbol.getScore();
    }

    public String getSymbolScoreByLetter(Symbol symbol) {
        return symbol.getLetter();

    }
}