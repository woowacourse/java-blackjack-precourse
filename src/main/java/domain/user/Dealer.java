package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
	private final List<Card> cards = new ArrayList<>();

	public Dealer() {
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return cards;
	}

	public boolean isBust() {
		int score = 0;
		for (Card card : cards) {
			score += card.getSymbol().getScore();
		}
		if (score > 21) {
			return true;
		}
		return false;
	}

	public boolean isBlackJack() {
		if (getScoreAceAsOne() == 21 || getScoreAceAsEleven() == 21) {
			return true;
		}
		return false;
	}

	public int getScoreAceAsOne() {
		int score = 0;
		for (Card card : cards) {
			score += card.getSymbol().getScore();
		}
		return score;
	}

	public int getScoreAceAsEleven() {
		int score = 0;
		for (Card card : cards) {
			score += aceToEleven(card.getSymbol().getScore());
		}
		return score;
	}

	private int aceToEleven(int symbol) {
		if (symbol == 1) {
			return 11;
		}
		return symbol;
	}
}
