package domain.user;

import domain.card.Card;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
	private static final double BLACKJACK = 21;
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();
    
    public void addCard(Card card) {
        cards.add(card);
    }

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public List<String> getPlayerSymbolAndType() {
    	List<String> temp = new ArrayList<>();
    	for (Card card : cards) {
    		temp.add(card.getCardSymbolType());
    	}
    	return temp;
    }
    
    public boolean findBlackJack() {
    	return numberSum() == BLACKJACK;
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
