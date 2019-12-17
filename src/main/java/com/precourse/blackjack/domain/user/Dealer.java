package com.precourse.blackjack.domain.user;

import com.precourse.blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
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

		while (totalScore > 21 && hasAce()) {
			totalScore -= 10;
		}
		return totalScore;
	}

	private boolean hasAce() {
		return cards.stream()
			.anyMatch(card -> card.getScore() == 11);
	}
}
