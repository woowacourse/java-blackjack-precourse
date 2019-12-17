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
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Card card = (Card)o;
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

	public String getCardData() {
		int numScore=symbol.getScore();
		String score=Integer.toString(numScore);
		if(numScore==1){
			score="A";
		}
		return score+type.getName();
	}

	public int getSymbolScore() {
		return symbol.getScore();
	}
}
