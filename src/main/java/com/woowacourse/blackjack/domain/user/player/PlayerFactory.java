package com.woowacourse.blackjack.domain.user.player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 플레이어의 이름과 베팅액을 받아 플레이어 리스트를 만들어주는 유틸리티 클래스
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-16
 */
public class PlayerFactory {
	private static final String LIST_SIZE_MISMATCH_EXCEPTION = "이름의 수와 베팅액의 수가 일치하지 않습니다.";

	public static List<Player> createPlayers(List<Name> names, List<BettingMoney> bettingMonies) {
		validateListSize(names, bettingMonies);
		return IntStream.range(0, names.size())
				.mapToObj(index -> new Player(names.get(index), bettingMonies.get(index)))
				.collect(Collectors.toList());
	}

	private static void validateListSize(List<Name> names, List<BettingMoney> bettingMonies) {
		if (names.size() != bettingMonies.size()) {
			throw new IllegalArgumentException(LIST_SIZE_MISMATCH_EXCEPTION);
		}
	}
}
