package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private CardFactory cardFactory = new CardFactory();
	private List<Card> deck = new ArrayList<>();
	
	public void createCardSet() {
		deck = cardFactory.create();
	}
	
	public void shuffleCards() {
		Collections.shuffle(deck);
	}
	
	public Card drawCard() {
		return deck.remove(0);
	}

}
