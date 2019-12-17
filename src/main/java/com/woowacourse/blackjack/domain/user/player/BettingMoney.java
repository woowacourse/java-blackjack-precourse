package com.woowacourse.blackjack.domain.user.player;

/**
 * 유저의 베팅 금액을 의미하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-15
 */
public class BettingMoney {
	private static final String OUT_OF_RANGE_MONEY_EXCEPTION = "1원 이상 베팅하셔야 합니다.";
	private static final double MIN_BETTING_MONEY = 1;

	private final double money;

	public BettingMoney (double money) {
		validateBettingMoney(money);
		this.money = money;
	}

	private void validateBettingMoney(double bettingMoney) {
		if (bettingMoney < MIN_BETTING_MONEY) {
			throw new IllegalArgumentException(OUT_OF_RANGE_MONEY_EXCEPTION);
		}
	}

	public static BettingMoney valueOf(String text) {
		return new BettingMoney(Double.parseDouble(text));
	}

	public double get() {
		return money;
	}

	@Override
	public String toString() {
		return String.valueOf(money);
	}
}
