package domain.user;

import static controller.BlackjackController.*;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Symbol;

public abstract class BlackjackMember {

	private final List<Card> deck = new ArrayList<>();
	private int currentMoney = ZERO;

	public abstract String getName();

	public void addCard(Card card) {
		deck.add(card);
	}

	public int getScoreSum() {
		int scoreSum = ZERO;

		for (Card card : deck) {
			scoreSum += card.getScore();
		}

		return checkAce(scoreSum);
	}

	private int checkAce(int scoreSum) {
		for (Card card : deck) {
			scoreSum = addAceScore(scoreSum, card);
		}

		return scoreSum;
	}

	private int addAceScore(int scoreSum, Card card) {
		if (card.getSymbol() == Symbol.ACE && scoreSum + ACE_TERM < BLACKJACK) {
			scoreSum += ACE_TERM;
		}

		return scoreSum;
	}

	private boolean isBlackjack() {
		return getScoreSum() == BLACKJACK;
	}

	public List<Card> getDeck() {
		return deck;
	}
}
