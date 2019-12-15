/*
 * InputUtil.java                        1.0.0   2019-12-13
 *
 * Copyright (c) 2019 Hyungju An.
 * All rights reserved.
 * Contact me for more information. a301dks@naver.com
 */

package com.precourse.blackjack.domain.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * 인풋 유틸리티 클래스입니다.
 *
 * @author HyungjuAn
 */
public class InputUtil {
	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static final String SPACE = " ";
	private static final String EMPTY = "";
	private static final String COMMA = ",";
	private static final String PLAYER_NAME_BLANK = "Player name is blank";
	private static final String PLAYER_NAME_DUPLICATION = "Player name is duplicate";
	private static final String PLAYER_WRONG_COUNT = "The number of players is wrong";
	private static final String BETTING_WRONG_MONEY = "Betting money is less than minimum";
	private static final int SPLIT_LIMIT = -1;
	private static final int MINIMUM_PLAYER_NUMBER = 1;
	private static final int MAXIMUM_PLAYER_NUMBER = 7;
	private static final int MINIMUM_BETTING_MONEY = 1000;

	public static List<String> readPlayersName() throws IOException {
		List<String> playersName = Arrays.asList(reader.readLine().replaceAll(SPACE, EMPTY).split(COMMA, SPLIT_LIMIT));

		try {
			checkPlayersName(playersName);
		} catch (IOException e) {
			throw e;
		}
		return playersName;
	}

	private static void checkPlayersName(List<String> playersName) throws IOException {
		try {
			checkNameEmpty(playersName);
			checkNameDuplicate(playersName);
			checkNameCount(playersName);
		} catch (IOException e) {
			throw e;
		}
	}

	private static void checkNameEmpty(List<String> playersName) throws IOException {
		if (playersName.contains(EMPTY)) {
			throw new IOException(PLAYER_NAME_BLANK);
		}
	}

	private static void checkNameDuplicate(List<String> playersName) throws IOException {
		if (playersName.stream().distinct().count() != playersName.size()) {
			throw new IOException(PLAYER_NAME_DUPLICATION);
		}
	}

	private static void checkNameCount(List<String> playersName) throws IOException {
		if ((playersName.size() < MINIMUM_PLAYER_NUMBER) || (playersName.size() > MAXIMUM_PLAYER_NUMBER)) {
			throw new IOException(PLAYER_WRONG_COUNT);
		}
	}

	public static double readPlayerBettingMoney() throws IOException, NumberFormatException {
		double playerBettingMoney;

		try {
			playerBettingMoney = Integer.parseInt(reader.readLine());
			checkMinimumBettingMoney(playerBettingMoney);
		} catch (IOException | NumberFormatException e) {
			throw e;
		}
		return playerBettingMoney;
	}

	private static void checkMinimumBettingMoney(double playerBettingMoney) throws IOException {
		if (playerBettingMoney < MINIMUM_BETTING_MONEY) {
			throw new IOException(BETTING_WRONG_MONEY);
		}
	}
}
