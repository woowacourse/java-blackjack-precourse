package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를h 의미하는 객체
 */
public class Player {
	private final String name;
	private final double bettingMoney;
	private final List<Card> cards = new ArrayList<>();

	public Player(String name, double bettingMoney) {
		this.name = name;
		this.bettingMoney = bettingMoney;
	}

	public String getName() {
		return name;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void showHand() {
		String showHand = this.name;
		for (Card card : this.cards) {
			showHand += card.toString() + ",";
		}
		System.out.println(showHand);
	}

	public Status checkStatus() {
		final int BLACKJACK = 21;

		int[] cardInfos = getScoreAndNumberOfAce();
		if (cardInfos[0] > BLACKJACK) {
			return Status.BUSTED;
		} else if ( cardInfos[0] == BLACKJACK ) {
			return Status.BLACKJACK;
		}
		return Status.KEEP_GO;
	}

	public int[] getScoreAndNumberOfAce() {
		int score = 0;
		int aceCount = 0;

		for (Card card : this.cards) {
			aceCount = setAceCount(card, aceCount);
			score += card.getSymbolScore();
		}
		int[] cardInfos = {score, aceCount};
		return cardInfos;
	}

	public int setAceCount(Card card, int aceCount) {
		if (card.isSymbolAce()) {
			aceCount++;
		}
		return aceCount;
	}
}
