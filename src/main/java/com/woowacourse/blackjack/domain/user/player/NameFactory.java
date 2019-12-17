package com.woowacourse.blackjack.domain.user.player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 유저의 입력값을 받아 유효한 이름 리스트로 만들어주는 유틸리티 클래스
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-16
 */
public class NameFactory {
	private static final String PLAYER_COUNT_OUT_OF_RANGE_EXCEPTION = "플레이어 수가 부족하거나 초과했습니다.";
	private static final String DUPLICATED_NAME_EXCEPTION = "중복된 플레이어 이름이 있습니다.";
	private static final String COMMA = ",";
	private static final int MIN_PLAYER_COUNT = 1;
	private static final int MAX_PLAYER_COUNT = 8;

	public static List<Name> createDistinctPlayerNames(String text) {
		List<Name> names = Arrays.stream(text.split(COMMA))
				.map(String::trim)
				.map(Name::new)
				.collect(Collectors.toList());
		return validateNames(names);
	}

	private static List<Name> validateNames(List<Name> names) {
		Objects.requireNonNull(names);
		validatePlayerCount(names);
		validatePlayerDuplicated(names);
		return names;
	}

	private static void validatePlayerCount(List<Name> names) {
		if (names.size() < MIN_PLAYER_COUNT || names.size() > MAX_PLAYER_COUNT) {
			throw new IllegalArgumentException(PLAYER_COUNT_OUT_OF_RANGE_EXCEPTION);
		}
	}

	private static void validatePlayerDuplicated(List<Name> names) {
		Set<Name> distinctNames = new HashSet<>(names);
		if (distinctNames.size() != names.size()) {
			throw new IllegalArgumentException(DUPLICATED_NAME_EXCEPTION);
		}
	}
}
