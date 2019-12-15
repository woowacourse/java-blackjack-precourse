package domain.card;

import java.util.Objects;

public class Card {
	private final Symbol symbol;
	private final Type type;

	public Card(Symbol symbol, Type type) {
		this.symbol = symbol;
		this.type = type;
	}

	/* 과제 시작시 주어진 메서드 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return symbol == card.symbol &&
				type == card.type;
	}

	/* 과제 시작시 주어진 메서드 */
	@Override
	public int hashCode() {
		return Objects.hash(symbol, type);
	}

	/* 과제 시작시 주어졌지만 내 맘대로 바꾼 메서드 */
	@Override
	public String toString() {
		return this.symbol.toStringSymbol()
				+ this.type.toStringType();
	}

	public int getScore() {
		return this.symbol.getScore();
	}
}
