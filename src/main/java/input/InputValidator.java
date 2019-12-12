/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package input;

import static view.PrintController.*;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 사용자의 입력에 대해 유효성 검사를 하는 클래스입니다.
 * @since : 2019.12.12 목요일
 */
public class InputValidator {
    private static String BLANK = " ";
    private static String REGEX = "[,]+";
    private static int ONE = 1;

    private static String inputString;
    private static double InputDouble;

    public static boolean inputStringValidator(String userInputString) {
        inputString = userInputString;
        return checkNameIsEmpty() && checkNameHasBlank() && checkNameIsComma();
    }

    public static boolean checkNameIsEmpty() {
        if (inputString.isEmpty()) {
            printInputEmptyError();
            return false;
        }
        return true;
    }

    public static boolean checkNameHasBlank() {
        if (inputString.contains(BLANK)) {
            printInputHasBlankError();
            return false;
        }
        return true;
    }

    public static boolean checkNameIsComma() {
        if (inputString.matches(REGEX)) {
            printInputIsCommaError();
            return false;
        }
        return true;
    }

    public static boolean inputDoubleValidator(double userInputDouble) {
        InputDouble = userInputDouble;
        return checkNumberIsBiggerThanZero();
    }

    public static boolean checkNumberIsBiggerThanZero() {
        if (InputDouble < ONE) {
            printInputNumberIsSmallError();
            return false;
        }
        return true;

    }
}
