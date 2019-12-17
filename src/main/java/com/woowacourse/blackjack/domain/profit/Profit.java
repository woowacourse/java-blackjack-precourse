package com.woowacourse.blackjack.domain.profit;

import java.util.Objects;

import com.woowacourse.blackjack.domain.user.player.Name;

/**
 * 플레이어의 수익을 의미하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-15
 */
public class Profit {
	private final Name name;
	private final double profit;

	public Profit(Name name, double profit) {
		this.name = Objects.requireNonNull(name);
		this.profit = profit;
	}

	@Override
	public String toString() {
		return String.format("%s: %.0f", name, profit);
	}

	public double getProfit() {
		return profit;
	}
}
