package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public abstract class User {
	private final List<Card> cards = new ArrayList<>();
	private final int blackJackCard = 2;
	protected final int Jack = 21;
	
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public List<Card> getCards() {
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
	
	public boolean checkBlackJack() {
		if(cards.size() == blackJackCard && getScore() == Jack) {
			return true;
		}
		return false;
	}
}
