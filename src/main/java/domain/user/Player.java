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
		super();
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
		final int INDEX_ONE = 1;
		final int INDEX_ZERO = 0;
		String hand = this.name;

		if (isDealer()) {
			hand = setHandString(INDEX_ONE);
		} else if (!isDealer()) {
			hand = setHandString(INDEX_ZERO);
		}
		System.out.println(hand + " result: " + getScore());
	}

	private boolean isDealer() {
		return this instanceof Dealer;
	}

	private String setHandString(int index) {
		String hand = this.name;
		for (; index < this.cards.size(); index++) {
			hand += cards.get(index).toString();
		}
		return hand;
	}

	public Status checkStatus() {
		int score = getScore();
		if (score > BlackJackConfig.BLACKJACK) {
			return Status.BUSTED;
		} else if (score == BlackJackConfig.BLACKJACK) {
			return Status.BLACKJACK;
		}
		return Status.KEEP_GO;
	}

	public int getScore() {
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
		if (ace) {
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
