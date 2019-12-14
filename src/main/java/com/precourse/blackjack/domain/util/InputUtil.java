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
import java.util.ArrayList;
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
	private static final int SPLIT_LIMIT = -1;

	public static List<String> readPlayersName() throws IOException {
		String[] playersName = reader.readLine().replaceAll(SPACE, EMPTY).split(COMMA, SPLIT_LIMIT);

		try {
			checkNameEmpty(playersName);
		} catch (IOException e) {
			throw e;
		}
		return Arrays.asList(playersName);
	}

	private static void checkNameEmpty(String[] playersName) throws IOException {
		if (Arrays.asList(playersName).contains(EMPTY)) {
			throw new IOException("Player's name is empty");
		}
	}
}
