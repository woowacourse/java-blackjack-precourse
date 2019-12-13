package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
	private static final String WHITE_SPACE = " ";

	private final String name;
	private final double bettingMoney;
	private final List<Card> cards = new ArrayList<>();

	public Player(String name, double bettingMoney) {
		validateName(name);
		this.name = name;
		this.bettingMoney = bettingMoney;
	}

	public static void validateName(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (name.contains(WHITE_SPACE)) {
			throw new IllegalArgumentException();
		}
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public String getName() {
		return name;
	}

	public double getBettingMoney() {
		return bettingMoney;
	}
}
