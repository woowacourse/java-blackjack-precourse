package domain.user;

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

	public void addCard(Card card) {
		cards.add(card);
	}

	// TODO 추가 기능 구현
	public String getName() {
		return this.name;
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public int getScore() {
		int sum = 0;
		for (Card card : cards) {
			sum += calculate(sum, card.getSymbol().getScore());
		}
		return sum;
	}

	private int calculate(int sum, int score) {
		if (score == 1) {
			return oneOrEleven(sum);
		}
		return score;
	}

	private int oneOrEleven(int sum) {
		if (sum <= 10) {
			return 11;
		}
		return 1;
	}
}
