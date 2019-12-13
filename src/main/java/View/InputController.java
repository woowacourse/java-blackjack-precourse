package View;

import domain.user.User;

public class InputController {
    private static final String NOT_A_NUMBER_EXCEPTION_MESSAGE = "숫자만 입력해주세요.";

    public static String askPlayerNames() {
        try {
            return InputView.inputPlayerNames();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askPlayerNames();
        }
    }

    public static double askBettingMoney(String playerName) {
        try {
            return InputView.inputBettingMoney(playerName);
        } catch (NumberFormatException e) {
            System.out.println(NOT_A_NUMBER_EXCEPTION_MESSAGE);
            return askBettingMoney(playerName);
        }
    }

    public static boolean askIfGetCard(User user) {
        try {
            String input = InputView.inputIfGetCard(user);
            InputValidator.validateIfGetCard(input);
            return stringToBoolean(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askIfGetCard(user);
        }
    }

    private static boolean stringToBoolean(String input) {
        if ("y".equals(input)) {
            return true;
        }
        return false;
    }
}
