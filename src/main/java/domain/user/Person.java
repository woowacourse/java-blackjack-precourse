package domain.user;

import domain.card.Card;
import domain.project.Constant;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private final List<Card> cards = new ArrayList<>();
	private String cardString;
	private int cardSum;
	
	public Person() {}
	
	public void addCard(Card card) {
        cards.add(card);
    }
	
	public void setCardString() {
		cardString = "";
		ArrayList<String> cardStringSet = new ArrayList<String>();
		
		for (Card c : cards) {
			cardStringSet.add(c.makeCardString());
		}
		cardString = String.join(", ", cardStringSet);
	}
	
	public String getCardString() {
		return this.cardString;
	}
	
	public void calculateCardSum() {
		cardSum = 0;
		
		for (Card c : cards) {
			cardSum = cardSum + c.getSymbolScore();
		}
	}
}
