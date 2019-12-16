package domain.user;

import domain.card.Card;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
	private final int firstCard = 0;
	private final int secondDealOutCriteria = 17;
	
	public Dealer() {
		setName("딜러");
	}
    // TODO 추가 기능 구현
	
	public void showFirstCard() {
		System.out.println(getCards().get(firstCard));
	}
	
	public boolean isBelowCriteria() {
		int score = 0;
		
		for (Card card : getCards()) {
			score += card.getScore();
		}
		
		if (score < secondDealOutCriteria) {
			return true;
		}
		return false;
	}
}
