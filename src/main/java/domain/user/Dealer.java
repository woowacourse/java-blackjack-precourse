package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public String showCardInfo() {
    	String cardInfo = "";
    	for (Card card : cards) {
    		cardInfo += card.toString() + " ";
    	}
    	return cardInfo;
    }
    
    public int getDealerScore() {
    	return cards.stream()
    				.mapToInt(card -> card.getScore())
    				.sum();
    }
}
