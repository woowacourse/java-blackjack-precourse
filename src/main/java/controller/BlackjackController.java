package controller;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.BlackjackMember;
import domain.user.BlackjackEntry;

public class BlackjackController {
	public static final int MAX = 21;
	public static final int ACE_TERM = 10;
	public static final int ZERO = 0;

	private List<Card> cards;

	BlackjackController() {
		cards = new ArrayList<>();

	}

	private List<Card> initCards() {
		List<Card> cards = new ArrayList<>();

		for (Card card : CardFactory.create()) {
			cards.add(card);
		}

		return cards;
	}

	private void firstServe(BlackjackEntry blackjackEntry) {
		for (BlackjackMember blackjackMember : blackjackEntry.getEntry()) {
			blackjackMember.addCard(cards.remove(ZERO));
		}
	}
}
