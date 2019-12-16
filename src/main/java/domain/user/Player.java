package domain.user;

import domain.card.Card;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Person {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }
    
    @Override
    public void addCard(Card card) {
        super.addCard(card);
    }

    // TODO 추가 기능 구현
    public String getName() {
    	return this.name;
    }
}
