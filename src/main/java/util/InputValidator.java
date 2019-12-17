package util;

import game.GameConstants;
import view.InputView;

public class InputValidator {

    public static boolean isCorrectChoice(String input) {
        if (input.equals(GameConstants.YES) || input.equals(GameConstants.NO)) {
            return true;
        }
        InputView.printChoiceError();
        return false;
    }

    public static boolean isNumberInRange(String input, int min) {
        try {
            int inputNumber = Integer.parseInt(input);
            return checkRange(inputNumber, min);
        } catch (NumberFormatException e) {
            InputView.printNumberError();
            return false;
        }
    }

    public static boolean checkRange(int input, int min) {
        if (input > min) {
            return true;
        }

        InputView.printNumberError();
        return false;
    }
}