/*
 * OutputView.java                        1.1.0   2019-12-12
 *
 * Copyright (c) 2019 Hyungju An.
 * All rights reserved.
 * Contact me for more information. a301dks@naver.com
 */

package com.precourse.blackjack.view;

/**
 * 출력을 담당하는 클래스입니다.
 *
 * @version 1.1.0			플레이어 이름을 묻는 기능 추가
 * @date 2019-12-12
 * @author HyungjuAn
 */
public class OutputView {
	private static final String PLAYER_NAME_QUESTION = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";

	protected void printPlayersNameQuestion() {
		System.out.println(PLAYER_NAME_QUESTION);
	}
}
