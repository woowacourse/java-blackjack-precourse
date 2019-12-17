package com.woowacourse.blackjack.domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.woowacourse.blackjack.domain.card.Card;
import com.woowacourse.blackjack.domain.card.Symbol;

/**
 * 블랙잭 딜러와 플레이어의 공통 역할을 의미하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-15
 */
public abstract class Gamer {
	private static final String NAME_DELIMITER = ", ";
	public static final int MAX_ACE_UPGRADE_SCORE = 11;
	public static final int ACE_UPGRADE_SCORE = 10;
	public static final int BLACKJACK_SCORE = 21;
	public static final int INITIAL_DRAW_COUNT = 2;

	private final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	public void addCards(List<Card> card) {
		cards.addAll(card);
	}

	public Card getCard(int index) {
		return cards.get(index);
	}

	// 다른 곳에서 리스트에 수정을 가하는 메서드 호출 불가. (읽기 전용)
	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public int getCardCount() {
		return cards.size();
	}

	public String getCardNames() {
		return cards.stream()
				.map(Card::toString)
				.collect(Collectors.joining(NAME_DELIMITER));
	}

	public boolean isBlackjack() {
		return isInitialDraw() && isMaxScore();
	}

	private boolean isInitialDraw() {
		return getCardCount() == INITIAL_DRAW_COUNT;
	}

	public boolean isMaxScore() {
		return getScore() == BLACKJACK_SCORE;
	}

	public boolean isBust() {
		return getScore() > BLACKJACK_SCORE;
	}

	public int getScore() {
		int score = getMinScore();
		if (hasAce() && canUpgradeAce(score)) {
			score += ACE_UPGRADE_SCORE;
		}
		return score;
	}

	// 모든 ACE를 1로 취급한다.
	private int getMinScore() {
		return getCards().stream()
				.mapToInt(Card::getScore)
				.sum();
	}

	private boolean hasAce() {
		return hasSymbol(Symbol.ACE);
	}

	private boolean canUpgradeAce(int score) {
		return score <= MAX_ACE_UPGRADE_SCORE;
	}

	private boolean hasSymbol(Symbol symbol) {
		return cards.stream()
				.anyMatch(card -> card.isSameSymbol(symbol));
	}
}
