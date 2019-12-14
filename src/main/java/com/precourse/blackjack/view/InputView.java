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
	private static final String PLAYER_NAME_QUESTION = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";

	public static void printPlayerNameQuestion() {
		System.out.println(PLAYER_NAME_QUESTION);
	}
}
