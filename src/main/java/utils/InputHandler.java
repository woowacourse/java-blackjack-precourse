package utils;

public class InputHandler {
    private static final int MIN_PLAYERS_COUNT = 2;
    private static final int MAX_PLAYERS_COUNT = 8;
    
    public static String[] splitByComma(String playerName) {
        String[] playerNames = playerName.split(",");
        if (InputValidator.isDuplicated(playerNames)) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }
        if (InputValidator.isEmptyLine(playerNames)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        if (playerNames.length < MIN_PLAYERS_COUNT || playerNames.length > MAX_PLAYERS_COUNT) {
            throw new IllegalArgumentException("참가하는 인원은 최소 2명, 최대 8명이어야 합니다.");
        }
        return playerNames;
    }
}
