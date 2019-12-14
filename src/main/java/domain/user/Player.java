package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User{
	private final String name;
	private final double bettingMoney;

	public Player(String name, int bettingMoney) {
		this.name = name;
		this.bettingMoney = bettingMoney;
	}

	// TODO 추가 기능 구현
	public static void checkValidName(String name) {
		PlayerConstraints.checkEmptyName(name);
	}

	public static void checkValidBettingMoney(int money) {
		PlayerConstraints.checkMoneyRange(money);
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getBettingMoney() {
		return this.bettingMoney;
	}
}
