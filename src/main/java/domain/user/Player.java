package domain.user;

import domain.card.Card;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
    private final String name;
    private final int bettingMoney;
    private final int Jack = 21;

    public Player(String name, int bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }
    
    // TODO 추가 기능 구현
    
    public String getName() {
    	return name;
    }
    
    public void showFirstCard() {
    	showResult();
    	System.out.println();
    }
    
    public boolean isBelowJack() {
    	int score = 0;
  
    	for (Card card : getCards()) {
    		score += card.getScore();
    	}
    	
    	if (score < Jack) {
    		return true;
    	}
    	return false;
    }
    
    public int getBettingMoney() {
    	return bettingMoney;
    }
}
