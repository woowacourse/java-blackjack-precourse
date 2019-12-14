package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
	private final int firstCard = 0;
	
	public Dealer() {
		setName("딜러");
	}
    // TODO 추가 기능 구현
	
	public void showFirstCard() {
		System.out.println(getCards().get(firstCard));
	}
}
