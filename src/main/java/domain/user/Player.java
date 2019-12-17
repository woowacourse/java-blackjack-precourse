package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private double bettingMoney;
    private final List<Card> cards = new ArrayList<>();
    private int score = 0;
    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }
    public void setBettingMoney(double money) {
    	this.bettingMoney += money;
    }
    public void printHands() {
    	System.out.print(name+"의 카드 : ");
    	for(int i=0;i<cards.size()-1;i++) {
    		System.out.print(cards.get(i).print()+", ");
    	}
    	System.out.print(cards.get(cards.size()-1).print());
    	System.out.println();
    }
    public void setScore() {
    	score = 0;
    	for(int i=0;i<cards.size();i++) {
    		score = score + cards.get(i).getScore();
    	}
    }
    public int getScore() {
    	return this.score;
    }
    public boolean isBlackJack() {
    	return score==21;
    }
    public double getBettingMoney() {
    	return this.bettingMoney;
    }
    public String getName() {
    	return this.name;
    }
    public int getGapScore() {
    	return 21-getScore();
    }//gap between score and 21
    // TODO 추가 기능 구현

}
