/*
 * OutputView.java                 1.0.0   2019-12-12
 *
 * Copyright (c) 2019 Hyungju An.
 * All rights reserved.
 * Contact me for more information. a301dks@naver.com
 */

package com.precourse.blackjack.view;

/**
 * 출력 뷰를 담당하는 클래스입니다.
 *
 * @author HyungjuAn
 */
public class OutputView {
	private static final String WRONG_PLAYER_NAME_INPUT = "1명 이상 7명 이하의 중복되지 않는 이름을 입력하세요.";
	private static final String WRONG_BETTING_MONEY_INPUT = "1000원 이상의 자연수를 입력하세요.";
	private static final String INITIAL_CARD_DEALING_END = "에게 2장의 카드를 나누었습니다.";

	public static void printWrongPlayerNameInput() {
		System.out.println(WRONG_PLAYER_NAME_INPUT);
	}

	public static void printWrongBettingMoneyInput() {
		System.out.println(WRONG_BETTING_MONEY_INPUT);
	}

	public static void printInitialCardDealingEnd(String names) {
		System.out.println(names + INITIAL_CARD_DEALING_END);
	}

	public static void printCards(String nameAndCards) {
		System.out.println(nameAndCards);
	}
}
