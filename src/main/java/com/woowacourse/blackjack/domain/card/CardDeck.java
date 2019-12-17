package com.woowacourse.blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * 카드 뭉치를 의미하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-15
 */
public class CardDeck {
	private final Stack<Card> cards;

	public CardDeck(List<Card> cards) {
		this.cards = new Stack<>();
		this.cards.addAll(cards);
		shuffle();
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public List<Card> draw(int count) {
		List<Card> cards = new ArrayList<>();
		IntStream.range(0, count).forEach(it -> cards.add(draw()));
		return cards;
	}

	public Card draw() {
		return cards.pop();
	}
}
