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

    }

	public abstract String getFirstCard();
	
	public abstract String getName();
	
	public String getCardResult() {
		String cardName = cards
				.stream()
				.map(card -> card.toString())
				.collect(Collectors.joining(", "));
		
		return cardName;
	}
	
	public int getScore() {
		int score = 0;
		
		for (Card card : cards) {
			score += card.getScore();
		}
		
		return score;
	}
}
