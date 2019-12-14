package com.woowahan.user;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
	private final String name;
	private final double bettingMoney;

	public Player(String name, double bettingMoney) {
		this.name = name;
		this.bettingMoney = bettingMoney;
	}

	@Override
	public String toString() {
		return name + "의 카드: " + getCardsStringWithComma();
	}

	public String getName() {
		return name;
	}

	public double getBettingMoney() {
		return bettingMoney;
	}
}
