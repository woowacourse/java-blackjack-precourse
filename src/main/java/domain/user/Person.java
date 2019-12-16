package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private final List<Card> cards = new ArrayList<>();
	private final ArrayList<String> stringList = new ArrayList();
	
	public Person() {};
	
	public List<Card> getCardList() {
		return this.cards;
	}
}
