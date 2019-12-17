package UI.Input;

import UI.Output.OutputController;

import java.util.Arrays;
import java.util.HashMap;

public class InputController {
    private static final String DELIMITER_FOR_SPLIT = ",";
    private static final String NOT_A_NUMBER_EXCEPTION_MESSAGE = "숫자만 입력해주세요.";

    public static HashMap<String, Double> askPlayerPropertiesAndHandleError() {
        try {
            return askPlayerProperties();
        } catch (IllegalArgumentException e) {
            OutputController.printMessage(e.getMessage());
            return askPlayerPropertiesAndHandleError();
        }
    }

    private static HashMap<String, Double> askPlayerProperties() throws IllegalArgumentException {
        String playerNames = askPlayerNames();
        String[] playerNamesHandled = handlePlayerNames(playerNames);

        return askBettingMoneyByPlayerName(playerNamesHandled);
    }

    private static String askPlayerNames() {
        try {
            return InputView.inputPlayerNames();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askPlayerNames();
        }
    }

    private static String[] handlePlayerNames(String playerNames) throws IllegalArgumentException {
        String[] playerNamesSplit = playerNames.split(DELIMITER_FOR_SPLIT);
        String[] playerNamesHandled = trimPlayerNames(playerNamesSplit);
        InputValidator.validatePlayerNamesSplit(playerNamesHandled);
        return playerNamesHandled;
    }

    private static String[] trimPlayerNames(String[] playerNames) {
        return Arrays.stream(playerNames)
                .map(playerName -> playerName.trim())
                .toArray(String[]::new);
    }

    private static HashMap<String, Double> askBettingMoneyByPlayerName(String[] playerNamesHandled) {
        HashMap<String, Double> playerProperties = new HashMap<>();

        for (String playerName : playerNamesHandled) {
            Double bettingMoney = askBettingMoney(playerName);
            playerProperties.put(playerName.trim(), bettingMoney);
        }

        return playerProperties;
    }

    private static double askBettingMoney(String playerName) {
        try {
            Double bettingMoney = Double.parseDouble(InputView.inputBettingMoney(playerName));
            InputValidator.validateBettingMoney(bettingMoney);
            return bettingMoney;
        } catch (NumberFormatException e1) {
            OutputController.printMessage(NOT_A_NUMBER_EXCEPTION_MESSAGE);
            return askBettingMoney(playerName);
        } catch (IllegalArgumentException e) {
            OutputController.printMessage(e.getMessage());
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
