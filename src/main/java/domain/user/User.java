package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class User {
	private static final int ZERO_SCORE = 0;
	private static final int ACE_EXTRA_SCORE = 10;
	private static final int OPTIMAL_SCORE = 21;
	private static final String NAME = "사용자";

	private final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	public String getName() {
		return NAME;
	}

	public List<Card> getCards() {
		return cards;
	}

	private int calculateAce(Card card, int score) {
		if (!card.isAce()) {
			return ZERO_SCORE;
		}
		if (score + ACE_EXTRA_SCORE <= OPTIMAL_SCORE) {
			return ACE_EXTRA_SCORE;
		}
		return ZERO_SCORE;
	}

	public int getScore() {
		int score = cards.stream().mapToInt(Card::getScore).sum();
		for (Card card : cards) {
			score += calculateAce(card, score);
		}
		return score;
	}

	public boolean isCardSize(int size) {
		return cards.size() == size;
	}

	public boolean isScore(int score) {
		return getScore() == score;
	}

	public boolean isScoreLessThan(int score) {
		return getScore() < score;
	}

	public boolean isScoreGreaterThan(int score) {
		return getScore() > score;
	}
}
