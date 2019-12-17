package com.precourse.blackjack.domain.user;

import com.precourse.blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Dealer {
	private static final int MAXIMUM_SCORE = 21;
	private final String name;
	private final double bettingMoney;
	private final List<Card> cards = new ArrayList<>();

	public Player(String name, double bettingMoney) {
		this.name = name;
		this.bettingMoney = bettingMoney;
	}

	// TODO 추가 기능 구현
	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean canHit() {
		return super.getTotalScore() < MAXIMUM_SCORE;
	}
}
