package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class User {
	private final List<Card> cards = new ArrayList<>();
	private String name;
	
    public void addCard(Card card) {
        cards.add(card);
    }
    
    protected void setName(String name) {
    	this.name = name;
    }
    
    protected List<Card> getCards() {
    	return cards;
    }
    
    public void showFirstResult() {
    	System.out.print(name + " 카드 : ");
    	showFirstCard();
    }

	public void showFirstCard() {}
	
	public String getName() {
		return name;
	}
	
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
