/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * InputExceptionController.java
 * 입력된 데이터를 검증하는 클래스
 *
 * @author      Sorin Jin
 * @version     1.0
 * @date        16 Dec 2019
 *
 */

package controller;

import view.Message;
import view.OutputView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class InputExceptionController {
    private final String DEALER_NAME = "딜러";
    private final String BLANK = " ";
    private final String NONE = "";
    private static final OutputView outputView = new OutputView();

    public boolean isValidName(List<String> playerNames) {
        List<String> playerNameRemoveBlank = new ArrayList<>();
        for (String name : playerNames) {
            playerNameRemoveBlank.add(name.replace(BLANK,NONE));
        }
        if (hasBlankName(playerNameRemoveBlank)) {
            outputView.print(Message.ERROR_BLANK_NAME.getMessage());
            return false;
        }
        if (notExistPlayer(playerNameRemoveBlank)) {
            outputView.print(Message.ERROR_PLAYER_NOT_EXIST.getMessage());
            return false;
        }
        if (hasDuplicateNames(playerNameRemoveBlank)) {
            outputView.print(Message.ERROR_DUPLICATE_NAME.getMessage());
            return false;
        }
        if (hasDealerName(playerNameRemoveBlank)) {
            outputView.print(Message.ERROR_NAME_IS_DEALER.getMessage());
            return false;
        }
        return true;
    }

    private boolean hasBlankName(List<String> playerName) {
        return playerName.contains(NONE);
    }

    private boolean notExistPlayer(List<String> playerName) {
        return playerName.isEmpty();
    }

    private boolean hasDuplicateNames(List<String> playerName) {
        return !(playerName.size() == new HashSet<>(playerName).size());
    }

    private boolean hasDealerName(List<String> playerName) {
        return playerName.contains(DEALER_NAME);
    }
}
