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
    
    public String getDealerSymbolAndType() {
    	return cards.get(0).getCardSymbolType();
    }
    
    public void getPlayerSymbolAndType(List<String> playerCards) {
    	for (Card card : cards) {
    		playerCards.add(card.getCardSymbolType());
    	}
    }
    
}
