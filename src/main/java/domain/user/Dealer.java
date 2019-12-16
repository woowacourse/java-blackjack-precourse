package domain.user;

import domain.card.Card;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
	private final int firstCard = 1;
	private final int secondDealOutCriteria = 17;
	
    // TODO 추가 기능 구현
	
	public String getName() {
		return "딜러";
	}
	
	public String getFirstCard() {
		return getCards().get(firstCard).toString();
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
