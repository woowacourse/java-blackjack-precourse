package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public abstract class User {
	private final List<Card> cards = new ArrayList<>();
	private final int blackJackCardCnt = 2;
	protected final int Jack = 21;
	
    public void addCard(Card card) {
        cards.add(card);
    }
    
    protected List<Card> getCards() {
    	return cards;
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
		int score = cards
				.stream()
				.collect(Collectors.summingInt(card -> card.getScore()));
		
		return getRealScore(score);
	}
	
	public int getRealScore(int score) {
		if (score > Jack && hasAce()) {
			return score - 10;
		}
		return score;
	}
	
	public boolean hasAce() {
		boolean hasAce = false;

		for (Card card : cards) {
			hasAce = isAce(card, hasAce);
		}
		
		return hasAce;
	}
	
	public boolean isAce(Card card, boolean hasAce) {
		if (card.isAce() || hasAce) {
			return true;
		}
		return false;
	}
	
	public boolean isBelow(int criteria) {
		int score = getCards()
				.stream()
				.collect(Collectors.summingInt(card -> card.getScore()));
		
		if (score < criteria) {
			return true;
		}
		return false;
	}
	
	public boolean isBlackJack() {
		if (cards.size() == blackJackCardCnt && getScore() == Jack) {
			return true;
		}
		return false;
	}
}
