package domain.card;

import java.util.Set;
import java.util.stream.Collectors;

public class CardDeck {
	private Set<Card> cards;
	
	public CardDeck() {
		cards = CardFactory.create();
		System.out.println(this.toString());
	}
	
	public String toString() {
		return cards.stream()
				.map(Card::toString)
				.collect(Collectors.joining("\n"));
	}
}
