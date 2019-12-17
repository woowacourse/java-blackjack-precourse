package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Person {
	private final static int DEALER_NUM = 16;

	public Dealer() {
	    super("딜러");
    }

	public boolean checkDealerScore() {
		for (int i = 0; i < ARR_NUM; i++) {
			if (cardSumList.get(i) < DEALER_NUM) {
				return true;
			}
		}
		return false;
	}
}
