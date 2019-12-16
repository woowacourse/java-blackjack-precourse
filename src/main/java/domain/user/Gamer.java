package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class Gamer {
	protected static final int JACKPOT = 21;
	protected static final int INIT_SCORE = 0;
	protected static final int ACE = 1;
	protected static final int ELEVEN = 11;
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
		int score = INIT_SCORE;
		for (Card card : cards) {
			score += card.getSymbol().getScore();
		}
		if (score > JACKPOT) {
			return true;
		}
		return false;
	}

	public boolean isBlackJack() {
		return false;
	}

	public int getBestScore() {
		if (getScoreAceAsEleven() > JACKPOT) {
			return getScoreAceAsOne();
		}
		return getScoreAceAsEleven();
	}

	public int getScoreAceAsOne() {
		int score = INIT_SCORE;
		for (Card card : cards) {
			score += card.getSymbol().getScore();
		}
		return score;
	}

	public int getScoreAceAsEleven() {
		int score = INIT_SCORE;
		for (Card card : cards) {
			score += aceToEleven(card.getSymbol().getScore());
		}
		return score;
	}

	public int aceToEleven(int symbol) {
		if (symbol == ACE) {
			return ELEVEN;
		}
		return symbol;
	}
}
