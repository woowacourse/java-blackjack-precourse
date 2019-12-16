package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardDrawer;
import domain.card.Symbol;

public class Gamer {
	private static final int BLACKJACK_NUMBER = 21;
	private static final int ACE_BONUS = 10;
	private static final int INITIAL_NUMBER_OF_CARDS = 2;
    private final List<Card> cards = new ArrayList<>();
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public void draw() {
    	addCard(CardDrawer.draw());
    }
    
    public int calculateScore() {
    	int score = 0;
    	
    	for (Card card : cards) {
    		score += card.getSymbolScore();
    	}
    	
    	return score;
    }
    
    public int calculateFinalScore() {
    	int score = calculateScore();
    	
    	for (Card card : cards) {
    		score += adjustAceScore(score, card);
    	}
    	
    	return score;
    }
    
    private int adjustAceScore(int score, Card card) {
    	if (score <= BLACKJACK_NUMBER - ACE_BONUS &&
    			card.isTheSymbol(Symbol.ACE)) {
			return ACE_BONUS;
		}
    	
    	return 0;
    }
    
    public boolean isBlackJack() {
    	return calculateFinalScore() == BLACKJACK_NUMBER && 
    			cards.size() == INITIAL_NUMBER_OF_CARDS;
    }
    
    public boolean isBust() {
    	return calculateFinalScore() > BLACKJACK_NUMBER;
    }
}
