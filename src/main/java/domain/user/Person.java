package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.AceCardSum;
import domain.card.Card;
import domain.game.GameUI;

public class Person {
	private final static int END_WORD = 2;
	private final static int ARR_NUM = 2;
	private final static int ACE = 1;
	private final List<Card> cards = new ArrayList<>();
	private final String name;
	private int cardSum;
	private ArrayList<Integer> cardSumList;
	private ArrayList<ArrayList<Integer>> aceSumList;
	private boolean blackJack;
	private int aceCardNum;

	public Person(String name) {
		this.name = name;
		this.cardSum = 0;
		this.blackJack = false;
		this.aceSumList = AceCardSum.createAceSumList();
	}

	public String getName() {
		return name;
	}

	public void addCard(Card card) {
		aceCheck(card);
		calculateCard(card);
		cards.add(card);
	}

	public void printCard() {
		StringBuilder cardList = new StringBuilder();
		cardList.append(name + "의 카드 : ");
		for (int i = 0; i < cards.size(); i++) {
			cardList.append(cards.get(i).getCard() + ", ");
		}
		cardList.delete(cardList.length() - END_WORD, cardList.length());
		GameUI.printPersonCard(cardList);
	}

	private void aceCheck(Card card) {
		if (card.getSymbol() == ACE) {
			aceCardNum += 1;
		}
	}

	private void calculateCard(Card card) {
		for (int i = 0; i < ARR_NUM; i++) {
			aceValidate(card);
		}
		cardSumList.clear();
		if (aceCardNum == 0) {
			noneAceCardCal();
			return;
		}
		existAceCardCal();
	}

	private void existAceCardCal(){
		for (int i = 0; i < ARR_NUM; i++) {
			cardSumList.add(cardSum + aceSumList.get(aceCardNum - 1).get(i));
		}
	}
	private void noneAceCardCal() {
		cardSumList.add(cardSum);
	}

	private void aceValidate(Card card) {
		if (card.getSymbol() != ACE) {
			cardSum += card.getSymbol();
		}
	}
}
