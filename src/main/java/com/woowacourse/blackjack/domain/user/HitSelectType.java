package com.woowacourse.blackjack.domain.user;

import java.util.Arrays;

/**
 * 유저가 카드를 계속 받을지 여부를 식별하는 열거형
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-15
 */
public enum HitSelectType {
	HIT("y"),
	STAND("n");

	private String select;

	HitSelectType(String select) {
		this.select = select;
	}

	public static HitSelectType of(String input) {
		return Arrays.stream(HitSelectType.values())
				.filter(it -> it.select.equals(input))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 선택 문자입니다."));
	}
}
