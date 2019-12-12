/*
 * BlackjackMain.java                        1.0.0   2019-12-12
 *
 * Copyright (c) 2019 Hyungju An.
 * All rights reserved.
 * Contact me for more information. a301dks@naver.com
 */

package com.precourse.blackjack;

import java.io.IOException;
import java.util.List;

import com.precourse.blackjack.controller.GameController;
import com.precourse.blackjack.domain.card.Card;

/**
 * 블랙잭 게임을 진행하는 메인 클래스입니다.
 *
 * @author HyungjuAn
 */
public class BlackjackMain {
	public static void main(String[] args) throws IOException {
		List<Card> allCards = GameController.createCards();
	}
}
