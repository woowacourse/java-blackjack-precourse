/*
 * OutputUtil.java                 1.0.0   2019-12-16
 *
 * Copyright (c) 2019 Hyungju An.
 * All rights reserved.
 * Contact me for more information. a301dks@naver.com
 */

package com.precourse.blackjack.domain.util;

import java.util.ArrayList;
import java.util.List;

import com.precourse.blackjack.domain.user.Dealer;
import com.precourse.blackjack.domain.user.Player;

/**
 * 아웃풋 유틸 클래스입니다.
 *
 * @author HyungjuAn
 */
public class OutputUtil {
	private static final String AND = "와 ";
	private static final String COMMA = ", ";
	private static final String COLON = ": ";
	private static final String HIDDEN = "HIDDEN";

	public static String getAllNames(List<Player> players, Dealer dealer) {
		StringBuilder builder = new StringBuilder();
		List<String> playersName = new ArrayList<>();

		players.forEach(player -> playersName.add(player.getName()));
		builder.append(dealer.getName()).append(AND);
		builder.append(String.join(COMMA, playersName));
		return builder.toString();
	}

	public static String getPlayerNameAndCards(Player player) {
		StringBuilder builder = new StringBuilder();
		List<String> cardsInformation = new ArrayList<>();

		builder.append(player.getName()).append(COLON);
		player.getCards().forEach(card -> cardsInformation.add(card.toString()));
		builder.append(String.join(COMMA, cardsInformation));
		return builder.toString();
	}

	public static String getDealerFirstCards(Dealer dealer) {
		StringBuilder builder = new StringBuilder();
		List<String> cardsInformation = new ArrayList<>();

		builder.append(dealer.getName()).append(COLON);
		cardsInformation.add(HIDDEN);
		dealer.getCards().stream().skip(1).forEach(card -> cardsInformation.add(card.toString()));
		builder.append(String.join(COMMA, cardsInformation));
		return builder.toString();
	}
}
