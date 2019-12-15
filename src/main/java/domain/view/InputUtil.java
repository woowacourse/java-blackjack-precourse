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
    // ENUM 활용해서 리팩토링해보기
    static final String YES = "y";
    static final String NO = "n";

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
        throw new InputMismatchException("1, 11 중에 입력하셨어야죠! 판 엎습니다!");
    }

    public static boolean inputAddCardQuestion() {
        String inputAddCardQuestion = SCANNER.nextLine().trim();
        if(inputAddCardQuestion.equals(YES)){
            return true;
        }
        if(inputAddCardQuestion.equals(NO)){
            return false;
        }
        throw new InputMismatchException("y, n 중에 입력하셨어야죠! 판 엎습니다!");
    }
}
