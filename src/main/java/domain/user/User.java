package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class User {
	private final List<Card> cards = new ArrayList<>();

	public User() {

	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void printCards() {
		for (Card card : cards) {
			System.out.print(card.toString());
		}
		System.out.println();
	}
}
