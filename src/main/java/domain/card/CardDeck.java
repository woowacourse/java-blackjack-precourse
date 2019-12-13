package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
	private final List<Card> cards = new ArrayList<>();

	public CardDeck() {
		CardFactory.create().forEach(cards::add);
		Collections.shuffle(cards);
	}
}
