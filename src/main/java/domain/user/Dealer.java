package domain.user;

import java.util.stream.Collectors;

public class Dealer extends User{
	private static final int FIRST = 1;
	
	public Dealer() {}

	public String getCardsExceptFirstInString() {
		return this.cards.stream()
				.skip(FIRST)
				.map(card -> card.toString())
				.collect(Collectors.joining(", "));
	}
}
