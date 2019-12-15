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
	private static final String WRONG_PLAYER_NAME_INPUT = "1명 이상 7명 이하의 이름을 입력하세요.";

	public static void printWrongPlayerNameInput() {
		System.out.println(WRONG_PLAYER_NAME_INPUT);
	}
}
