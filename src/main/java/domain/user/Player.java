package domain.user;

import domain.card.Card;
import domain.card.CardSupplier;
import domain.card.KoreanSymbol;
import domain.card.Symbol;
import domain.view.InputView;
import domain.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
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

}
