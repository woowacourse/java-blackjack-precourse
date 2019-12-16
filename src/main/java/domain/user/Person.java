package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private final List<Card> cards = new ArrayList<>();
	private String cardString;
	
	public Person() {}
	
	public void addCard(Card card) {
        cards.add(card);
    }
	
	public void setCardString() {
		cardString = "";
		
		for (Card c : cards) {
			cardString = cardString + c.makeCardString();
		}
	}
}
