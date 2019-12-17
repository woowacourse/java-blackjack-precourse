package com.woowacourse.blackjack.domain.user.player;

import java.util.Objects;

/**
 * 유저의 이름을 의미하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-15
 */
public class Name {
	private static final String NAME_LENGTH_OUT_OF_RANGE_EXCEPTION = "이름의 길이는 1~5사이여야 합니다.";
	private static final String HAS_SPACE_EXCEPTION = "이름에는 공백이 들어갈 수 없습니다.";
	private static final String SPACE = " ";
	private static final int MIN_NAME_LENGTH = 1;
	private static final int MAX_NAME_LENGTH = 5;

	private final String name;

	public Name(String name) {
		Objects.requireNonNull(name);
		validateNameRange(name);
		validateHasSpace(name);
		this.name = name;
	}

	private void validateNameRange(String name) {
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException(NAME_LENGTH_OUT_OF_RANGE_EXCEPTION);
		}
	}

	private void validateHasSpace(String name) {
		if (name.contains(SPACE)) {
			throw new IllegalArgumentException(HAS_SPACE_EXCEPTION);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Name compareTargetName = (Name)o;
		return Objects.equals(name, compareTargetName.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return name;
	}
}
