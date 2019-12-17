package utils;

public class InputHandler {
    public static String[] splitByComma(String playerName) {
        checkValidity(playerName.split(","));
        return playerName.split(",");
    }

    private static void checkValidity(String[] playerNames) {
        InputValidator.checkEmpty(playerNames);
        InputValidator.checkNameRange(playerNames);
    }
}
