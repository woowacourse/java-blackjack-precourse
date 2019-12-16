package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public abstract class User {
	private final List<Card> cards = new ArrayList<>();
	
    public void addCard(Card card) {
        cards.add(card);
    }
    
    protected List<Card> getCards() {
    	return cards;
    }
    
    public void showFirstResult() {
    	System.out.print(getName() + " 카드 : ");
    	showFirstCard();
    }

	public abstract void showFirstCard();
	
	public abstract String getName();
	
	public void showResult() {
		String cardName = cards
				.stream()
				.map(card -> card.toString())
				.collect(Collectors.joining(", "));
		
		System.out.print(cardName);
	}
	
	public int getScore() {
		int score = 0;
		
		for (Card card : cards) {
			score += card.getScore();
		}
		
		return score;
	}
}
