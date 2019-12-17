package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.AceCardSum;
import domain.card.Card;
import domain.game.GameUI;

/**
 * Person
 * 버전 1.0
 * 딜러와 플레이어 공통 기능을 수행을 위한 클래스
 */
public class Person {
	protected final static int END_WORD = 2;
	protected final static int ARR_NUM = 2;
	protected final static int BLACK_JACK = 21;
	protected final static int ACE = 1;
	protected final List<Card> cards = new ArrayList<>();
	protected final String name;
	protected int cardSum;
	protected ArrayList<Integer> cardSumList;
	protected ArrayList<ArrayList<Integer>> aceSumList;
	protected boolean blackJack;
	protected int aceCardNum;

	public Person(String name) {
		this.name = name;
		this.cardSum = 0;
		this.blackJack = false;
		this.cardSumList = new ArrayList<Integer>();
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

	private void existAceCardCal() {
		for (int i = 0; i < ARR_NUM; i++) {
			checkBlackJack(cardSum + aceSumList.get(aceCardNum - 1).get(i));
			cardSumList.add(cardSum + aceSumList.get(aceCardNum - 1).get(i));
		}
	}

	private void noneAceCardCal() {
		checkBlackJack(cardSum);
		cardSumList.add(cardSum);
	}

	private void aceValidate(Card card) {
		if (card.getSymbol() != ACE) {
			cardSum += card.getSymbol();
		}
	}

	private void checkBlackJack(int sum) {
		if (sum == BLACK_JACK) {
			blackJack = true;
		}
	}

	public boolean getBlackJack() {
		return blackJack;
	}
}
