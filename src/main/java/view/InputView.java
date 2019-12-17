/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * InputView.java
 * 입력을 담당하는 클래스
 *
 * @author      Sorin Jin
 * @version     1.0
 * @date        16 Dec 2019
 *
 */

package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import util.InputExceptionController;

public class InputView {
    private final String SEPARATOR = ",";
    public static final String YES = "y";
    public static final String NO = "n";
    private OutputView outputView = new OutputView();
    private Scanner scanner = new Scanner(System.in);

    public List<String> getPlayerName() {
        List<String> playerNameList;
        do {
            outputView.whatIsPlayerName();
            playerNameList = Arrays.asList(scanner.nextLine().split(SEPARATOR));
        } while (!InputExceptionController.getInstance().isValidName(playerNameList));
        return playerNameList;
    }

    public int getBettingMoney(String playerName) {
        String money;
        do {
            outputView.howMuchBettingMoney(playerName);
            money = scanner.nextLine();
        } while (!InputExceptionController.getInstance().isValidBettingMoney(money));
        return Integer.parseInt(money);
    }

    public boolean getMoreCard(String playerName) {
        String answer;
        do {
            outputView.wantMoreCard(playerName);
            answer = scanner.nextLine();
        } while (!InputExceptionController.getInstance().isValidMoreCard(answer));
        if (answer.equals(YES)) {
            return true;
        }
        return false;
    }
}
