/*
 * InputView.java                        1.0.0   2019-12-12
 *
 * Copyright (c) 2019 Hyungju An.
 * All rights reserved.
 * Contact me for more information. a301dks@naver.com
 */

package com.precourse.blackjack.view;

/**
 * 입력 뷰를 담당하는 클래스입니다.
 *
 * @author HyungjuAn
 */
public class InputView {
	private static final String PLAYER_NAME_QUESTION = "게임에 참여할 사람의 이름을 입력하세요. "
		+ "(2명 이상일 경우 쉼표(,)로 분리)";
	private static final String PLAYER_BETTING_QUESTION = "의 배팅 금액은?";
	private static final String HIT_CARD_QUESTION = "는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)";

	public static void printPlayerNameQuestion() {
		System.out.println(PLAYER_NAME_QUESTION);
	}

	public static void printPlayerBettingQuestion(String playerName) {
		System.out.println(playerName + PLAYER_BETTING_QUESTION);
	}

	public static void printHitCardQuestion(String playerName) {
		System.out.println(playerName + HIT_CARD_QUESTION);
	}
}
