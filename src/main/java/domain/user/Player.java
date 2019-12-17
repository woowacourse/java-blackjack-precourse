package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Person {
	private final double bettingMoney;
	private boolean addCardCheck;

	public Player(String name, double bettingMoney) {
		super(name);
		this.bettingMoney = bettingMoney;
		this.addCardCheck = true;
	}

	public double getMoney() {
		return bettingMoney;
	}

	public boolean checkAddCard() {
		return addCardCheck;
	}

	public void setAddCardCheck(boolean addCardCheck) {
		this.addCardCheck = addCardCheck;
	}

	//Player 점수가 21을 넘는지 안넘는지 체크
	public boolean checkPlayerScore() {
		for (int i = 0; i < ARR_NUM; i++) {
			if (cardSumList.get(i) < BLACK_JACK) {
				return true;
			}
		}
		return false;
	}

}
