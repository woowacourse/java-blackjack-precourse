package domain.user;

import domain.card.Card;

public abstract class User {
	int profit = 0;
	int sumNumbers = 0;
	
    abstract void addCard(Card card);
	
    boolean blackJackYN(int sumNumbers) {
    	if (sumNumbers == 21) {
    		return true;
    	}
    	return false;
    }
    
    boolean bustYN(int sumNumbers) {
    	if (sumNumbers > 21) {
    		return true;
    	}
    	return false;
    }
}
