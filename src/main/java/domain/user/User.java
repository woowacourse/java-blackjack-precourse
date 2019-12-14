package domain.user;

import domain.card.Card;
import java.util.List;

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

	void userCardInfo(List<Card> cards) {
		for (int i = 0; i < cards.size() - 2; i++) {
			System.out.print(cards.get(i).cardInfo() + ", ");
		}
		System.out.print(cards.get(cards.size() - 1).cardInfo());
	}

	void userCardInfo(List<Card> cards, String dealer) {
		System.out.print(cards.get(0).cardInfo());
	}
}
