/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package input;

import view.PrintController;

import static view.PrintController.*;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 사용자의 입력에 대해 유효성 검사를 하는 클래스입니다.
 * @since : 2019.12.17 화요일
 */
public class InputValidator {
    private static final String BLANK = " ";
    private static final String REGEX = "[,]+";
    private static final int ONE = 1;
    private static final String YES = "y";
    private static final String NO = "n";

    private static String inputString;
    private static double inputDouble;
    private static String inputAnswer;


    public static boolean inputStringValidator(String userInputString) {
        inputString = userInputString;
        return checkStringIsEmpty() && checkNameHasBlank() && checkNameIsComma();
    }

    public static boolean checkStringIsEmpty() {

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
        inputDouble = userInputDouble;
        return checkNumberIsBiggerThanZero();
    }

    public static boolean checkNumberIsBiggerThanZero() {

        if (inputDouble < ONE) {
            printInputNumberIsSmallError();
            return false;
        }

        return true;
    }

    public static boolean inputAnswerValidator(String userAnswer) {
        inputAnswer = userAnswer;
        return checkStringIsYesOrNo();
    }

    public static boolean checkStringIsYesOrNo() {

        if (!inputAnswer.equals(YES) && !inputAnswer.equals(NO)) {
            PrintController.printInputIsNotYOrNError();
            return false;
        }

        return true;
    }
}
