package domain.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
    static final Scanner SCANNER = new Scanner(System.in);
    static final String COMMA = ",";
    static final String SPACE = " ";
    static final String EMPTY_SPACE = "";
    static final String ONE = "1";
    static final String ELEVEN = "11";

    public static String[] inputName() {
        return SCANNER.nextLine().replace(SPACE, EMPTY_SPACE).split(COMMA);
    }

    public static int inputBettingMoney() {
        return SCANNER.nextInt();
    }

    public static Integer inputAceUse() {
        String inputAceUse = SCANNER.nextLine().trim();
        if (inputAceUse.equals(ONE)) {
            return Integer.parseInt(ONE);
        }
        if (inputAceUse.equals(ELEVEN)) {
            return Integer.parseInt(ELEVEN);
        }

        throw new InputMismatchException("잘못된 값을 입력했어요!");
    }
}
