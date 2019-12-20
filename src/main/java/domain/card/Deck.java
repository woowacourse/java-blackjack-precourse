package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import domain.card.*;
import view.Input;

public class Deck {
	private CardFactory cardFactory = new CardFactory();
	private view.Input input = new Input(); 
	private List<Card> cards = new ArrayList<>();
	
	public void makeCardSet() {
		cards = cardFactory.create();
		Collections.shuffle(cards);
	}
	
	public Card drawCard() {
		return cards.remove(0);
	}

}
