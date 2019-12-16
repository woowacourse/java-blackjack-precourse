package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.game.GameUI;

public class Person {
	private final static int END_WORD = 2;
	private final static int ARR_NUM = 2;
	private final static int MAX_SUM = 1;
	private final static int MIN_SUM = 0;
	private final static int ACE_MAX = 11;
	private final static int ACE = 1;
	private final List<Card> cards = new ArrayList<>();
	private final String name;
	private int[] cardSum;
	private int aceCardNum;
	private StringBuilder cardList;

	public Person(String name) {
		this.name = name;
		this.cardSum = new int[ARR_NUM];
		cardList = new StringBuilder();
	}

	public String getName() {
		return name;
	}

	public void addCard(Card card) {
		calculateCard(card);
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

	private void calculateCard(Card card) {
		if (card.getSymbol() == ACE) {
			aceCardNum += 1;
			cardSum[MIN_SUM] += card.getSymbol();
			cardSum[MAX_SUM] += ACE_MAX;
			return;
		}
		for(int i=0; i<ARR_NUM; i++){
			cardSum[i] += card.getSymbol();
		}
	}
}
