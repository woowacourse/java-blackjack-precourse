package com.woowahan.user;

import java.util.ArrayList;
import java.util.List;

import com.woowahan.card.Card;

public class User {
	protected final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	private void removeLastComma(String stringWithComma) {
		int len = stringWithComma.length();
		stringWithComma = stringWithComma.substring(0, len - 2);
	}

	protected String getCardsStringWithComma() {
		StringBuilder cardsStringWithComma = new StringBuilder();

		for (Card card : cards) {
			cardsStringWithComma.append(card.toString());
			cardsStringWithComma.append(", ");
		}

		removeLastComma(cardsStringWithComma.toString());

		return cardsStringWithComma.toString();
	}
}
