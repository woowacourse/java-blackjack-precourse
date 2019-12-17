package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class User {
	private static final int ACE = 1;
	
	private final List<Card> cards = new ArrayList<>();
	private int score;
	private boolean hasAce;

	public User() {

	}

	public void addCard(Card card) {
		if (card.getSymbol().getScore() == ACE) {
			hasAce = true;
		}
		score += card.getSymbol().getScore();
		cards.add(card);
	}

	public void printCards() {
		for (Card card : cards) {
			System.out.print(card.toString() + " ");
		}
		System.out.println();
	}

	public int getScore() {
		return score;
	}
	
	public boolean hasAce() {
		return hasAce;
	}
}
