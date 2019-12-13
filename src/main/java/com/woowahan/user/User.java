package com.woowahan.user;

import java.util.ArrayList;
import java.util.List;

import com.woowahan.card.Card;

public class User {
	protected final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}
}
