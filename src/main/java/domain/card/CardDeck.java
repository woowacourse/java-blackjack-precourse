package domain.card;

import java.util.List;
import java.util.stream.Collectors;

public class CardDeck {
	private List<Card> cards;
	
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
