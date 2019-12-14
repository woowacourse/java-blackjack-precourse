package com.woowahan.card;

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
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		Card card = (Card)object;
		return symbol == card.symbol && type == card.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, type);
	}

	@Override
	public String toString() {
		return type.toString() + symbol.toString();
	}

	public boolean isAce() {
		return symbol == Symbol.ACE;
	}

	public int getScore() {
		return symbol.getScore();
	}
}
