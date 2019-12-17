package com.precourse.blackjack.domain.user;

import com.precourse.blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Dealer {
	private static final int MAXIMUM_SCORE = 21;
	private static final double DEFEAT_RATE = -1;
	private static final double BLACKJACK_WIN_RATE = 1.5;
	private static final double DRAW = 0;

	private final String name;
	private final double bettingMoney;
	private final List<Card> cards = new ArrayList<>();

	public Player(String name, double bettingMoney) {
		this.name = name;
		this.bettingMoney = bettingMoney;
	}

	// TODO 추가 기능 구현
	public double getReturn(Dealer dealer) {
		if (isDefeat(dealer)) {
			return bettingMoney * DEFEAT_RATE;
		}
		if (isBlackjackWin(dealer)) {
			return bettingMoney * BLACKJACK_WIN_RATE;
		}
		if (isWin(dealer)) {
			return bettingMoney;
		}
		return DRAW;
	}

	private boolean isDefeat(Dealer dealer) {
		return ((dealer.isBlackjack() && !this.isBlackjack()) || this.isBurst()
			|| ((dealer.getTotalScore() > this.getTotalScore()) && !dealer.isBurst()));
	}

	private boolean isBlackjackWin(Dealer dealer) {
		return !dealer.isBlackjack() && this.isBlackjack();
	}

	private boolean isWin(Dealer dealer) {
		return dealer.isBurst() || (dealer.getTotalScore() < this.getTotalScore());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean canHit() {
		return super.getTotalScore() < MAXIMUM_SCORE;
	}
}
