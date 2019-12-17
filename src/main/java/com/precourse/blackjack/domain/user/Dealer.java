package com.precourse.blackjack.domain.user;

import com.precourse.blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
	private static final int ACE_SCORE = 11;
	private static final int MAXIMUM_SCORE = 21;
	private static final int ACE_TO_ONE = 10;

	private final List<Card> cards = new ArrayList<>();

	public Dealer() {
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	// TODO 추가 기능 구현
	public String getName() {
		return "딜러";
	}

	public List<Card> getCards() {
		return cards;
	}

	public int getTotalScore() {
		int totalScore = cards.stream()
			.mapToInt(card -> card.getScore())
			.sum();

		while (totalScore > MAXIMUM_SCORE && hasAce()) {
			totalScore -= ACE_TO_ONE;
		}
		return totalScore;
	}

	private boolean hasAce() {
		return cards.stream()
			.anyMatch(card -> card.getScore() == ACE_SCORE);
	}
}
