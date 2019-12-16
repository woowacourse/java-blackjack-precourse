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
import controller.InputExceptionController;

public class InputView {
    private final String SEPARATOR = ",";
    InputExceptionController inputExceptionController = new InputExceptionController();
    Scanner scanner = new Scanner(System.in);

    public List<String> getPlayerName() {
        List<String> playerName;
        do {
            System.out.println(Message.INPUT_PLAYER_NAME.getMessage());
            playerName = Arrays.asList(scanner.next().split(SEPARATOR));
        } while (!inputExceptionController.isValidName(playerName));
        return playerName;
    }

}
