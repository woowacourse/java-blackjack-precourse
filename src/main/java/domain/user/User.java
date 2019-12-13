package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.CardDeck;

public class User {
	private static final int ACE_SCORE = 1;
	private static final int ACE_UPDATE_SCORE = 10;
	private static final int BLACKJACK_SCORE = 21;
	
	private List<Card> cards = new ArrayList<Card>();
	
	public User() {};
	
	public List<Card> getCards() {
		return this.cards;
	}
	
	public void drawCard(CardDeck deck) {
		this.cards.add(deck.pop());
	}

	public int calculateScore() {
		int score = calculateWithAceAsOne();
		for (int i = 0; i < countNumOfAce(); i++) {
			score = updateScoreWithAce(score);
		}
		return score;
	}

	private int updateScoreWithAce(int score) {
		score += ACE_UPDATE_SCORE;
		if (score > BLACKJACK_SCORE) {
			score -= ACE_UPDATE_SCORE;
		}
		return score;
	}

	private int calculateWithAceAsOne() {
		return this.cards.stream()
				.map(Card::getScore)
				.mapToInt(Integer::intValue)
				.sum();
	}

	private int countNumOfAce() {
		return (int) this.cards.stream()
				.filter(card -> card.getScore() == ACE_SCORE)
				.count();
	}
	
	public String getAllCardsInString() {
		return this.cards.stream()
				.map(card -> card.toString())
				.collect(Collectors.joining(", "));
	}
}
