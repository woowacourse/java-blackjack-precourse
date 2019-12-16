package utils;

import java.util.ArrayList;

public class InputValidator {
    private static final int MIN_INPUT_NUMBER = 1;

    static boolean isDuplicated(String[] inputs) {
        ArrayList<String> names = new ArrayList<>();
        for (String inputName : inputs) {
            if (names.contains(inputName)) {
                return true;
            }
            names.add(inputName);
        }
        return false;
    }

    static boolean isEmptyLine(String[] inputs) {
        for (String inputName : inputs) {
            if (inputName.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidNumber(int inputNumber) {
        return inputNumber >= MIN_INPUT_NUMBER;
    }

    public static boolean isValidIntent(String inputIntent) {
        return inputIntent.toLowerCase().equals("y") || inputIntent.toLowerCase().equals("n");
    }
}
