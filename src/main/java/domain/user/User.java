package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.CardDeck;

public class User {
	private static final int ACE_SCORE = 1;
	private static final int ACE_UPDATE_SCORE = 10;
	private static final int UPDATE_THRESHOLD = 12;
	
	protected List<Card> cards = new ArrayList<Card>();
	
	public User() {};
	
	public List<Card> getCards() {
		return this.cards;
	}
	
	public void drawCard(CardDeck deck) {
		this.cards.add(deck.pop());
	}

	public int calculateScore() {
		int score = calculateWithAceAsOne();
		return updateAce(score);
	}

	private int calculateWithAceAsOne() {
		return this.cards.stream()
				.map(Card::getScore)
				.mapToInt(Integer::intValue)
				.sum();
	}
	
	private int updateAce(int score) {
		if (this.cards.stream().anyMatch(card -> card.getScore() == ACE_SCORE)
				&& score < UPDATE_THRESHOLD) {
			return score + ACE_UPDATE_SCORE; 
		}
		return score;
	}
	
	public String getAllCardsInString() {
		return this.cards.stream()
				.map(card -> card.toString())
				.collect(Collectors.joining(", "));
	}
}
