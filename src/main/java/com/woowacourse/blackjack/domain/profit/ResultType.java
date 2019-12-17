package com.woowacourse.blackjack.domain.profit;

import com.woowacourse.blackjack.domain.user.player.BettingMoney;

/**
 * 이율을 의미하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-16
 */
public enum ResultType {
	BLACKJACK(1.5),
	WIN(1.0),
	DRAW(0.0),
	LOSE(-1.0);

	private double rate;

	ResultType(double rate) {
		this.rate = rate;
	}

	public double getProfit(BettingMoney bettingMoney) {
		return bettingMoney.get() * rate;
	}
}
