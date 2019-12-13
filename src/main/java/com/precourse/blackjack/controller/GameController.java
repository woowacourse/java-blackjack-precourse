/*
 * GameController.java             1.0.0   2019-12-12
 *
 * Copyright (c) 2019 Hyungju An.
 * All rights reserved.
 * Contact me for more information. a301dks@naver.com
 */

package com.precourse.blackjack.controller;

import java.util.List;

import com.precourse.blackjack.domain.card.Card;
import com.precourse.blackjack.domain.card.CardFactory;
import com.precourse.blackjack.domain.game.Game;
import com.precourse.blackjack.domain.user.Player;
import com.precourse.blackjack.view.OutputView;

/**
 * 게임 컨트롤러 클래스입니다.
 *
 * @author HyungjuAn
 */
public class GameController {
	public static void startGame() {
		//Game.start();
	}

	public static List<Card> createCards() {
		return CardFactory.create();
	}
}
