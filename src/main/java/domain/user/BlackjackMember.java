package domain.user;

import static controller.BlackjackController.*;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Symbol;

public abstract class BlackjackMember {
	private double revenue = ZERO;
	private final List<Card> deck = new ArrayList<>();

	public abstract String getName();

	public void addCard(Card card) {
		deck.add(card);
	}

	private int getScoreSum() {
		int scoreSum = ZERO;

		for (Card card : deck) {
			scoreSum += card.getScore();
		}

		return checkAce(scoreSum);
	}

	private int checkAce(int scoreSum) {
		for (Card card : deck) {
			scoreSum = setAceScore(scoreSum, card);
		}

		return scoreSum;
	}

	private int setAceScore(int scoreSum, Card card) {
		if (card.getSymbol() == Symbol.ACE && scoreSum + ACE_TERM < BLACKJACK) {
			scoreSum += ACE_TERM;
		}

		return scoreSum;
	}

	public boolean isBlackjack() {
		return getScoreSum() == BLACKJACK;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public void winBetting(double bettingAmount) {
		revenue += bettingAmount;
	}

	public double loseBetting(double bettingAmount) {
		revenue -= bettingAmount;
		return bettingAmount;
	}

	public double getRevenue() {
		return revenue;
	}
}
