package domain.user;

import java.util.stream.Collectors;

import domain.card.Card;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
    private final String name;
    private final double bettingMoney;
    private final int Jack = 21;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
        setName(name);
    }
    
    // TODO 추가 기능 구현
    
    public void showFirstCard() {
    	String cardName = getCards()
    			.stream()
    			.map(card -> card.toString())
    			.collect(Collectors.joining(", "));
    			
    	System.out.println(cardName);
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
}
