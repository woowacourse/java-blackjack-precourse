/*
 * Game.java                       1.0.0   2019-12-13
 *
 * Copyright (c) 2019 Hyungju An.
 * All rights reserved.
 * Contact me for more information. a301dks@naver.com
 */

package com.precourse.blackjack.domain.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.precourse.blackjack.controller.GameController;
import com.precourse.blackjack.domain.card.Card;
import com.precourse.blackjack.domain.card.CardFactory;
import com.precourse.blackjack.domain.user.Dealer;
import com.precourse.blackjack.domain.user.Player;

/**
 * 블랙잭 게임을 의미하는 객체입니다.
 *
 * @author HyungjuAn
 */
public class Game {
	private static final String AND = "와 ";
	private static final String COMMA = ", ";
	private static final int ONE = 1;
	private static final int TWO = 2;
	private final Stack<Card> cardDeck;
	private final List<Player> players;
	private final Dealer dealer;

	public Game(List<Player> players) {
		this.cardDeck = new Stack<>();
		CardFactory.create().forEach(card -> cardDeck.push(card));
		this.players = players;
		this.dealer = new Dealer();
	}

	public void start() {
		Collections.shuffle(cardDeck);
		dealTwoCards();
		GameController.showInitialCardDealingEnd(getAllNames());
	}

	private void dealTwoCards() {
		for (int i = 0; i < TWO; i++) {
			players.forEach(player -> player.addCard(cardDeck.pop()));
			dealer.addCard(cardDeck.pop());
		}
	}

	public String getAllNames() {
		StringBuilder builder = new StringBuilder();
		List<String> playersName = new ArrayList<>();

		players.forEach(player -> playersName.add(player.getName()));
		builder.append(dealer.getName()).append(AND);
		builder.append(String.join(COMMA, playersName));
		return builder.toString();
	}
}
