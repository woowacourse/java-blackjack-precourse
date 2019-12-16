package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.game.GameUI;

public class Person {
	private final static int END_WORD = 2;
	private final List<Card> cards = new ArrayList<>();
	private final String name;
	private StringBuilder cardList;

	public Person(String name) {
		this.name = name;
		cardList = new StringBuilder();
	}

	public String getName() {
		return name;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void printCard() {
		cardList.append(name + "의 카드 : ");
		for (int i = 0; i < cards.size(); i++) {
			cardList.append(cards.get(i).getCard() + ", ");
		}
		cardList.delete(cardList.length() - END_WORD, cardList.length());
		GameUI.printPersonCard(cardList);
	}
}
