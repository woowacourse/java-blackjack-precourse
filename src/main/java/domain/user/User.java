package domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.card.Card;

public class User {
	private static final int ACE = 1;

	private final List<Card> cards = new ArrayList<>();
	private int score;
	private boolean hasAce;
	private boolean fail;

	public User() {
		this.fail = false;
	}

	public void setFail() {
		this.fail = true;
	}

	public void setScore(int score) {
		this.score = score;
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
		System.out.println(" - 결과: " + score);
	}

	public int getScore() {
		return score;
	}

	public boolean hasAce() {
		return hasAce;
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}
}
