package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현
    public int sumScore() {
    	int sum = 0;
    	for(int i=0; i<cards.size(); i++) {
    		sum += cards.get(i).getSymbol().getScore();
    	}
    	if(existAce() && sum <= 11) return sum + 10;
    	return sum;
    }
    
    public boolean isAce(Card card) {
    	return card.getSymbol().getFigure() == "A";
    }
    
    public boolean existAce() {
    	boolean flag = false;
    	for(int i=0; i<cards.size(); i++) {
    		flag = isAce(cards.get(i));
    	}
    	return flag;
    }
    
    public boolean neededExtra() {
    	return sumScore() <= 16;
    }
    
    @Override
    public String toString() {
    	String str = "딜러 : ";
    	String joinStr = String.join(",", cards.toString());
    	str += joinStr.substring(1, joinStr.length()-1);
    	return str;
    }
}
