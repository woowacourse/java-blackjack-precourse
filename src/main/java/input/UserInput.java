/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package input;

import domain.model.user.Player;
import view.PrintController;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 사용자로부터 입력을 받는 기능을 담당하는 클래스입니다.
 * @since : 2019.12.17 화요일
 */
public class UserInput {
    private static final int DOUBLE_TYPE_INPUT_WRONG_MARKER = 0;
    private static final String COMMA = ",";
    private static final String YES = "y";

    public List<String> getPlayerName() {
        String inputString;

        do {
            PrintController.askPlayerName();
            inputString = getInputString();
        } while (!InputValidator.inputStringValidator(inputString));

        return separateStringToPlayerNameList(inputString);
    }

    public String getInputString() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public List<String> separateStringToPlayerNameList(String inputString) {
        List<String> playerNameList;
        playerNameList = Arrays.asList(inputString.split(COMMA));
        return playerNameList;
    }

    public double getBettingMoney(String playerName) {
        double bettingMoney;

        do {
            PrintController.askBettingMoney(playerName);
            bettingMoney = getInputDouble();
        } while (!InputValidator.inputDoubleValidator(bettingMoney));

        return bettingMoney;
    }

    public double getInputDouble() {
        try {
            Scanner scan = new Scanner(System.in);
            return scan.nextDouble();
        } catch (InputMismatchException e) {
            return DOUBLE_TYPE_INPUT_WRONG_MARKER;
        }
    }

    public boolean getPlayerAnswer(Player player) {
        String inputString;

        if (player.isBlackJack()) {
            return false;
        }

        inputString = askRightAnswerLoop(player);
        return inputString.equals(YES);
    }

    public String askRightAnswerLoop(Player player) {
        String inputString;

        do {
            PrintController.askDrawMoreCard(player);
            inputString = getInputString();
        } while (!InputValidator.inputAnswerValidator(inputString));

        return inputString;
    }
}
