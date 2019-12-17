package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMA = ",";
    private static final String SPACE = " ";
    private static final String EMPTY_SPACE = "";
    private static final String YES = "y";
    private static final String NO = "n";

    public static String inputName() {
        return SCANNER.nextLine().replace(SPACE, EMPTY_SPACE);
    }

    public static int inputBettingMoney() {
        try {
            return Integer.parseInt(SCANNER.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("금액은 1이상의 숫자로 입력해주세요.");
        }
    }

    public static boolean inputAddCardQuestion() {
        String inputAddCardQuestion = SCANNER.nextLine().trim();
        if (inputAddCardQuestion.equals(YES)) {
            return true;
        }
        if (inputAddCardQuestion.equals(NO)) {
            return false;
        }
        throw new InputMismatchException("y, n 중에 입력해주세요.");
    }
}
