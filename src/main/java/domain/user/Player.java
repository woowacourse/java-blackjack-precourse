package domain.user;

import config.BlackJackConfig;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
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
		int score = getScoreAndNumberOfAce();
		if (score > BlackJackConfig.BLACKJACK) {
			return Status.BUSTED;
		} else if (score == BlackJackConfig.BLACKJACK) {
			return Status.BLACKJACK;
		}
		return Status.KEEP_GO;
	}

	private int getScoreAndNumberOfAce() {
		int score = 0;
		Boolean aceInHand = false;

		for (Card card : this.cards) {
			score += card.getSymbolScore();
			aceInHand = setAceCount(card);
		}
		return addScoreIfAceIsTrue(score, aceInHand);
	}

	private Boolean setAceCount(Card card) {
		if (card.isSymbolAce()) {
			return true;
		}
		return false;
	}

	private int addScoreIfAceIsTrue(int score, Boolean ace) {
		if (ace == true) {
			score = addScore(score);
		}
		return score;
	}

	// Score값이 0~21일때 ACE가 존재하면 score에 10을 더해주는 메소드
	private int addScore(int score) {
		if (score < BlackJackConfig.ADD_LIMIT) {
			score += BlackJackConfig.ACE_DIFFERENTIAL;
		}
		return score;
	}
}
