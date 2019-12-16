/*
 * Game.java                       1.0.0   2019-12-13
 *
 * Copyright (c) 2019 Hyungju An.
 * All rights reserved.
 * Contact me for more information. a301dks@naver.com
 */

package com.precourse.blackjack.domain.game;

import java.util.Collections;
import java.util.List;

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
	private final List<Card> cardDeck;
	private final List<Player> players;
	private final Dealer dealer;
	private int cardTop = -1;

	public Game(List<Player> players) {
		this.cardDeck = CardFactory.create();
		this.players = players;
		this.dealer = new Dealer();
	}

	public void start() {
		Collections.shuffle(cardDeck);
		dealTwoCards();
		GameController.showInitialCardDealingEnd(getAllNames());
	}

	private void dealTwoCards() {
		players.forEach(player -> player.addCard(cardDeck.get(++cardTop)));
		dealer.addCard(cardDeck.get(++cardTop));
	}

	public String getAllNames() {
		StringBuilder builder = new StringBuilder();

		builder.append(dealer.getName()).append(AND);
		for (Player nextPlayer : players) {
			builder.append(nextPlayer.getName()).append(COMMA);
		}
		return builder.toString();
	}
}
