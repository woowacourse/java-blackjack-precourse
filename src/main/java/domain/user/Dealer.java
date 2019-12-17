package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Symbol;

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
    
    public boolean findBlackJack() {
    	return numberSum() == 21;
    }
    
    private int numberSum() {
    	int numSum = 0;
    	for (int i = 0; i < cards.size(); i++) {
    		numSum += cards.get(i).getSymbol().getScore();
    	}
    	if (haveAce() && numberSum() <= 11) {
    		numSum += 10;
    	}
    	return numSum;
    }
    
    private boolean haveAce() {
    	return cards.contains(Symbol.ACE);
    }
}
