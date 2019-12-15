package domain.user;

import domain.card.Card;
import domain.card.Symbol;

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

	public double getBettingMoney() {
		return bettingMoney;
	}

	public List<Card> getCards() {
		return cards;
	}

	public boolean bust() {
		if (sumCardScore() > 21) {
			return true;
		}
		return false;
	}

	public int sumCardScore() {
		if (containAce()) {
			int sum_ace_eleven = sumScore() + 10;
			if (sum_ace_eleven <= 21) {
				return sum_ace_eleven;
			}
		}
		return sumScore();
	}

	private int sumScore() {
		int sum = 0;

		for (Card card : cards) {
			sum += card.getScore();
		}
		return sum;
	}

	public boolean isBlackJack() {
		if (cards.size() == 2 && containAce() && sumCardScore() == 21) {
			return true;
		}
		return false;
	}

	private boolean containAce() {
		for (Card card : cards) {
			if (isAce(card)) {
				return true;
			}
		}
		return false;
	}

	private boolean isAce(Card card) {
		return card.getSymbol().equals(Symbol.ACE);
	}
}
