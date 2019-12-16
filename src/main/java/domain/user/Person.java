package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class Person {
	private final List<Card> cards = new ArrayList<>();

	public Person() {
	}

	public void addCard(Card card) {
		cards.add(card);
	}

}
