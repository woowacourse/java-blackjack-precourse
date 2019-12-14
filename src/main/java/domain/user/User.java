package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class User {
	private static final int ZERO_SCORE = 0;
	private static final int ACE_EXTRA_SCORE = 10;
	private static final int OPTIMAL_SCORE = 21;

	private final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return cards;
	}

	private int calculateAce(Card card, int sum) {
		if (!card.isAce()) {
			return ZERO_SCORE;
		}
		if (sum + ACE_EXTRA_SCORE <= OPTIMAL_SCORE) {
			return ACE_EXTRA_SCORE;
		}
		return ZERO_SCORE;
	}

	public int getSumOfCards() {
		int sum = cards.stream().mapToInt(Card::getScore).sum();
		for (Card card : cards) {
			sum += calculateAce(card, sum);
		}
		return sum;
	}

	public boolean isCardSize(int size) {
		return cards.size() == size;
	}

	public boolean isScore(int score) {
		return getSumOfCards() == score;
	}

	public boolean isScoreLessThan(int score) {
		return getSumOfCards() < score;
	}
}
