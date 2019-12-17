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

	public int getSymbolScore() {
		return this.symbol.getScore();
	}

	public Boolean isSymbolAce() {
		if (this.symbol == Symbol.ACE) {
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
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
		if (this.symbol == Symbol.ACE) {
			return " A" + this.type;
		} else if (this.symbol == Symbol.KING) {
		    return " K" + this.type;
        } else if (this.symbol == Symbol.QUEEN) {
		    return " Q" + this.type;
        } else if (this.symbol == Symbol.JACK) {
		    return " J" + this.type;
        }
		return " " + this.symbol.getScore() + this.type;
		}
	}
