package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Person {
	private final double bettingMoney;

	public Player(String name, double bettingMoney) {
		super(name);
		this.bettingMoney = bettingMoney;
	}

	public double getMoney() {
		return bettingMoney;
	}
	// TODO 추가 기능 구현

}
