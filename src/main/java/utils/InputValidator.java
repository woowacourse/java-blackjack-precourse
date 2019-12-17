package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class InputValidator {
    private static final int MIN_PLAYERS_COUNT = 2;
    private static final int MAX_PLAYERS_COUNT = 8;
    private static final String IS_EMPTY_MESSAGE = "이름은 최소 1자 이상이어야 합니다.";
    private static final String IS_OUT_OF_RANGE_MESSAGE = "참가하는 인원은 최소 2명, 최대 8명이어야 합니다.";
    private static final int MIN_INPUT_NUMBER = 1;
    private static final String IS_INVALID_MONEY_MESSAGE = "잘못된 배팅 금액 입력입니다.";
    private static final String IS_INVALID_INTENT_MESSAGE = "y 또는 n 이외의 입력입니다.";

    static void checkEmpty(String[] playerNames) {
        if (isEmptyLine(playerNames)) {
            throw new IllegalArgumentException(IS_EMPTY_MESSAGE);
        }
    }

    private static boolean isEmptyLine(String[] names) {
        ArrayList<String> inputNames = new ArrayList<>(Arrays.asList(names));
        return inputNames.stream().anyMatch(String::isEmpty);
    }

    static void checkNameRange(String[] playerNames) {
        if (playerNames.length < MIN_PLAYERS_COUNT || playerNames.length > MAX_PLAYERS_COUNT) {
            throw new IllegalArgumentException(IS_OUT_OF_RANGE_MESSAGE);
        }
    }

    public static void checkMoneyValidity(int money) {
        if (money < MIN_INPUT_NUMBER) {
            throw new IllegalArgumentException(IS_INVALID_MONEY_MESSAGE);
        }
    }

    public static void checkIntentValidity(String intent) {
        if(!isValidIntent(intent)) {
            throw new IllegalArgumentException(IS_INVALID_INTENT_MESSAGE);
        }
    }

    private static boolean isValidIntent(String intent) {
        return intent.toLowerCase().equals("y") || intent.toLowerCase().equals("n");
    }
}
