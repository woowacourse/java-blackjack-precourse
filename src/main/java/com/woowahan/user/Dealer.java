package com.woowahan.user;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
	public Dealer() {
	}

	@Override
	public String toString() {
		return "딜러의 카드: " + getCardsStringWithComma();
	}
}
