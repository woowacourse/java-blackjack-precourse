package utils;

public class InputHandler {
    private static final int MIN_PLAYERS_COUNT = 2;
    private static final int MAX_PLAYERS_COUNT = 8;
    private static final String IS_DUPLICATED_MESSAGE = "중복된 이름이 존재합니다.";
    private static final String IS_EMPTY_MESSAGE = "이름은 최소 1자 이상이어야 합니다.";
    private static final String IS_OUT_OF_RANGE_MESSAGE = "참가하는 인원은 최소 2명, 최대 8명이어야 합니다.";

    public static String[] splitByComma(String playerName) {
        checkValidity(playerName.split(","));
        return playerName.split(",");
    }

    private static void checkValidity(String[] playerNames) {
        checkDuplication(playerNames);
        checkEmpty(playerNames);
        checkNameRange(playerNames);
    }

    private static void checkDuplication(String[] playerNames) {
        if (InputValidator.isDuplicated(playerNames)) {
            throw new IllegalArgumentException(IS_DUPLICATED_MESSAGE);
        }
    }

    private static void checkEmpty(String[] playerNames) {
        if (InputValidator.isEmptyLine(playerNames)) {
            throw new IllegalArgumentException(IS_EMPTY_MESSAGE);
        }
    }

    private static void checkNameRange(String[] playerNames) {
        if (playerNames.length < MIN_PLAYERS_COUNT || playerNames.length > MAX_PLAYERS_COUNT) {
            throw new IllegalArgumentException(IS_OUT_OF_RANGE_MESSAGE);
        }
    }
}
