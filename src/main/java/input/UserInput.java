/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package input;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static input.InputValidator.inputDoubleValidator;
import static input.InputValidator.inputStringValidator;
import static view.PrintController.askBettingMoney;
import static view.PrintController.askPlayerName;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 사용자로부터 입력을 받는 기능을 담당하는 클래스입니다.
 * @since : 2019.12.12 목요일
 */
public class UserInput {
    private static final int DOUBLE_TYPE_INPUT_WRONG_MARKER = 0;
    private static final String COMMA = ",";

    public List<String> getPlayerName() {
        List<String> playerNameList;
        String inputString;
        do {
            askPlayerName();
            inputString = getInputString();
        } while (!inputStringValidator(inputString));
        playerNameList = separateStringToPlayerNameList(inputString);
        return playerNameList;

    }

    public String getInputString() {
        Scanner scan = new Scanner(System.in);
        String inputString = scan.nextLine();
        return inputString;
    }

    public List<String> separateStringToPlayerNameList(String inputString) {
        List<String> playerNameList;
        playerNameList = Arrays.asList(inputString.split(COMMA));
        return playerNameList;
    }

    public double getBettingMoney(String playerName) {
        double bettingMoney;
        do {
            askBettingMoney(playerName);
            bettingMoney = getInputDouble();
        } while (!inputDoubleValidator(bettingMoney));
        return bettingMoney;
    }

    public double getInputDouble() {
        try {
            Scanner scan = new Scanner(System.in);
            double inputDouble = scan.nextDouble();
            return inputDouble;
        } catch (InputMismatchException e) {
            return DOUBLE_TYPE_INPUT_WRONG_MARKER;
        }
    }


}
