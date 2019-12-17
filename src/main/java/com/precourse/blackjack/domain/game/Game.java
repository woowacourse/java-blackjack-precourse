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
import java.util.Stack;

import com.precourse.blackjack.controller.GameController;
import com.precourse.blackjack.domain.card.Card;
import com.precourse.blackjack.domain.card.CardFactory;
import com.precourse.blackjack.domain.user.Dealer;
import com.precourse.blackjack.domain.user.Player;
import com.precourse.blackjack.domain.util.OutputUtil;

/**
 * 블랙잭 게임을 의미하는 객체입니다.
 *
 * @author HyungjuAn
 */
public class Game {
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
		setGame();
		proceedGame();
		endGame();
	}

	private void setGame() {
		Collections.shuffle(cardDeck);
		dealTwoCards();
		sendSettingEndMessage();
	}

	private void dealTwoCards() {
		for (int i = 0; i < TWO; i++) {
			players.forEach(player -> player.addCard(cardDeck.pop()));
			dealer.addCard(cardDeck.pop());
		}
	}

	private void sendSettingEndMessage() {
		GameController.showInitialCardDealingEnd(OutputUtil.getAllNames(players, dealer));
		GameController.showCards(OutputUtil.getDealerFirstCards(dealer));
		players.forEach(player -> GameController.showCards(OutputUtil.getPlayerNameAndCards(player)));
	}

	private void proceedGame() {
		if (dealer.isBlackjack()) {
			GameController.showDealerBlackjack();
			return;
		}
		players.forEach(player -> drawCard(player));
		if (!dealer.canHit()) {
			GameController.showDealerNoHit();
			return;
		}
		drawDealer();
	}

	private void drawCard(Player player) {
		while (player.canHit() && GameController.hitCard(player.getName())) {
			player.addCard(cardDeck.pop());
			GameController.showCards(OutputUtil.getPlayerNameAndCards(player));
		}
	}

	private void drawDealer() {
		while (dealer.canHit()) {
			GameController.showDealerHit();
			dealer.addCard(cardDeck.pop());
		}
	}

	private void endGame() {
		GameController.showGameResult(OutputUtil.getGameResult(players, dealer));
	}
}
