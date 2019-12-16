package domain.user;


import domain.card.Card;
import domain.game.BlackJack;
import domain.user.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
	private final List<Card> cards = new ArrayList<>();

	public Dealer() {
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void showHand() {
		String showHand = "딜러:";
		for (int i = 1; i < this.cards.size(); i++) {
			showHand += this.cards.get(i).toString() + ",";
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
