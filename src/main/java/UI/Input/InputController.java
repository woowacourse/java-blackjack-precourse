package UI.Input;

import domain.user.User;

import java.util.HashMap;

public class InputController {
    private static final String DELIMITER_FOR_SPLIT = ",";
    private static final String NOT_A_NUMBER_EXCEPTION_MESSAGE = "숫자만 입력해주세요.";

    public static HashMap<String, Double> askPlayerProperties() {
        HashMap<String, Double> playerProperties = new HashMap<>();

        String[] playerNamesSplit = askPlayerNames().split(DELIMITER_FOR_SPLIT);

        for (String playerName : playerNamesSplit) {
            Double bettingMoney = askBettingMoney(playerName);
            playerProperties.put(playerName, bettingMoney);
        }

        return playerProperties;
    }

    private static String askPlayerNames() {
        try {
            return InputView.inputPlayerNames();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askPlayerNames();
        }
    }

    private static double askBettingMoney(String playerName) {
        try {
            return Double.parseDouble(InputView.inputBettingMoney(playerName));
        } catch (NumberFormatException e) {
            System.out.println(NOT_A_NUMBER_EXCEPTION_MESSAGE);
            return askBettingMoney(playerName);
        }
    }

    public static boolean askIfGetCard(String name) {
        try {
            String input = InputView.inputIfGetCard(name).toLowerCase();
            InputValidator.validateIfGetCard(input);
            return stringToBoolean(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askIfGetCard(name);
        }
    }

    private static boolean stringToBoolean(String input) {
        return "y".equals(input);
    }
}
