package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class Gamer {
	private final List<Card> cards = new ArrayList<>();

	public Gamer() {
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
		return false;
	}
	
	public int getBestScore() {
		if(getScoreAceAsEleven() > 21) {
			return getScoreAceAsOne();
		}
		return getScoreAceAsEleven();
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

	public int aceToEleven(int symbol) {
		if (symbol == 1) {
			return 11;
		}
		return symbol;
	}
}
