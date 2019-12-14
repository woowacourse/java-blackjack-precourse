package com.woowahan.user;

import java.util.ArrayList;
import java.util.List;

import com.woowahan.card.Card;

public class User {
	protected final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	private void removeLastComma(StringBuilder stringWithComma) {
		int len = stringWithComma.length();
		stringWithComma.delete(len-2, len);
	}

	protected String getCardsStringWithComma() {
		StringBuilder cardsStringWithComma = new StringBuilder();
		for (Card card : cards) {
			cardsStringWithComma.append(card.toString());
			cardsStringWithComma.append(", ");
		}

		removeLastComma(cardsStringWithComma);

		return cardsStringWithComma.toString();
	}

	private boolean hasAce() {
		boolean hasAce = false;
		for (Card card : cards) {
			hasAce = hasAce || card.isAce();
		}
		
		return hasAce;
	}

	public int getScore(boolean isUpperAce) {
		int score = 0;
		for (Card card : cards) {
			score += card.getScore();
		}

		if (hasAce() && isUpperAce && score <= 11) {
			score += 10;
		}

		return score;
	}
}
