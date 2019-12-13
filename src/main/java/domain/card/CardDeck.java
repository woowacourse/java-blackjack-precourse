package domain.card;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
	private final Stack<Card> cards = new Stack<>();

	public CardDeck() {
		cards.addAll(CardFactory.create());
		Collections.shuffle(cards);
	}

	public Card draw() {
		return cards.pop();
	}
}
