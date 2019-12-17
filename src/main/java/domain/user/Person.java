package domain.user;

import domain.card.Card;
import domain.project.Constant;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private final List<Card> cards = new ArrayList<>();
	private String cardString;
	private int cardSum;
	private int cardSumWithAce;
	private boolean isContainAce;

	public Person() {}

	public void addCard(Card card) {
		cards.add(card);
		calculateCard();
	}

	public void setCardString() {
		cardString = "";
		ArrayList<String> cardStringSet = new ArrayList<String>();

		for (Card c : cards) {
			cardStringSet.add(c.makeCardString());
		}
		cardString = String.join(", ", cardStringSet);
	}
	
	public void setResultString() {
		String result = makeResult();
		
		setCardString();
		cardString = cardString + Constant.RESULT + result;
	}
	
	private String makeResult() {
		if (isContainAce == true && cardSumWithAce == Constant.BLACKJACK) {
			return Integer.toString(cardSumWithAce);
		}
		if (isContainAce == true && cardSumWithAce >= cardSum) {
			return Integer.toString(cardSumWithAce);
		}
		return Integer.toString(cardSum);
	}

	public String getCardString() {
		return this.cardString;
	}

	private void addCardNumber() {
		for (Card c : cards) {
			cardSum = cardSum + c.getSymbolScore();
		}
		addAce();
	}
	
	private void addAce() {
		if (isContainAce == true) {
			cardSumWithAce = cardSum + 10;
		}
	}

	private void checkContainAce() {
		for (Card c : cards) {
			checkCardSet(c);
		}
	}

	private void checkCardSet(Card card) {
		if (card.getSymbolName().equals(Constant.ACE)) {
			isContainAce = true;
			cardSumWithAce = 0;
		}
	}

	private void calculateCard() {
		cardSum = 0;
		isContainAce = false;
		checkContainAce();
		addCardNumber();
	}
	
	public boolean getIsContainAce() {
		return this.isContainAce;
	}
	
	public int getCardSum() {
		return this.cardSum;
	}
	
	public int getCardSumWithAce() {
		return this.cardSumWithAce;
	}
}
