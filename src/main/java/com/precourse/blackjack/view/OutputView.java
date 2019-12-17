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
	private static final String WRONG_HIT_CARD_INPUT = "잘못 입력하셨습니다.";
	private static final String DEALER_BLACKJACK = "딜러가 블랙잭입니다.";
	private static final String DEALER_NO_HIT = "딜러는 17이상이라 카드를 받지 않습니다.";
	private static final String DEALER_HIT = "딜러는 16이하라 한 장의 카드를 더 받았습니다.";

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

	public static void printWrongHitCardInput() {
		System.out.println(WRONG_HIT_CARD_INPUT);
	}

	public static void printDealerBlackjack() {
		System.out.println(DEALER_BLACKJACK);
	}

	public static void printDealerNoHit() {
		System.out.println(DEALER_NO_HIT);
	}

	public static void printDealerHit() {
		System.out.println(DEALER_HIT);
	}
}
