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

package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class InputExceptionController {
    private final String DEALER_NAME = "딜러";
    private final String BLANK = " ";
    private final String NONE = "";

    private InputExceptionController() {}

    private static class InnerInstanceClass {
        private static final InputExceptionController instance = new InputExceptionController();
    }

    public static InputExceptionController getInstance() {
        return InnerInstanceClass.instance;
    }

    public boolean isValidName(List<String> playerNames) {
        List<String> playerNameRemoveBlank = new ArrayList<>();
        for (String name : playerNames) {
            playerNameRemoveBlank.add(name.replace(BLANK,NONE));
        }
        if (hasBlankName(playerNameRemoveBlank)) {
            System.out.println(Message.ERROR_BLANK_NAME.getMessage());
            return false;
        }
        if (notExistPlayer(playerNameRemoveBlank)) {
            System.out.println(Message.ERROR_PLAYER_NOT_EXIST.getMessage());
            return false;
        }
        if (hasDuplicateNames(playerNameRemoveBlank)) {
            System.out.println(Message.ERROR_DUPLICATE_NAME.getMessage());
            return false;
        }
        if (hasDealerName(playerNameRemoveBlank)) {
            System.out.println(Message.ERROR_NAME_IS_DEALER.getMessage());
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

    public boolean isValidBettingMoney(String money) {
        if(!isNumber(money)) {
            System.out.println(Message.ERROR_NOT_A_NUMBER.getMessage());
            return false;
        }
        int bettingMoney = Integer.parseInt(money);
        if(!isRangeOfBettingMoney(bettingMoney)) {
            System.out.println(Message.ERROR_BETTING_MONEY_RANGE.getMessage());
            return false;
        }
        return true;
    }

    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean isRangeOfBettingMoney(int money) {
        return money > 0;
    }
}
