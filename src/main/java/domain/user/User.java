package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class User {
	private List<Card> cards = new ArrayList<Card>();
	
	public User() {};
	
    public List<Card> getCards() {
    	return this.cards;
    }
	
    public void drawCard(Card card) {
        this.cards.add(card);
    }
    
    public int calculateScore() {		// implement later
    	return 1;
    }
}
