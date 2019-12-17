package domain.user;

import static controller.BlackjackController.*;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Symbol;

public class BlackjackMember {
	private final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getScoreSum() {
		int scoreSum = ZERO;

		for (Card card : cards) {
			scoreSum += card.getScore();
		}

		return checkAce(scoreSum);
	}

	private int checkAce(int scoreSum) {
		for (Card card : cards) {
			scoreSum = addAceScore(scoreSum, card);
		}

		return scoreSum;
	}

	private int addAceScore(int scoreSum, Card card) {
		if (card.getSymbol() == Symbol.ACE && scoreSum + ACE_TERM < MAX) {
			scoreSum += ACE_TERM;
		}

		return scoreSum;
	}
}
