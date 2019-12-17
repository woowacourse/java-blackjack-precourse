/*
 * InputManager.java
 *
 * version 1.0
 *
 * 20191215
 *
 * copyright by jiwonSong(s26788761@naver.com)
 *
 */

package domain.game;

import domain.user.Player;

import java.util.*;

public class InputManager {
    private static final int MINIMUM_NAME_COUNT = 1;
    private static final int MINIMUM_NAME_LENGTH = 1;
    public static final String YES = "y";
    public static final String NO = "n";

    private Scanner scanner = new Scanner(System.in);

    public ArrayList<String> getPlayerNames() {
        String[] nameArray;

        do {
            print(Message.makeMessageAskPlayerNames());
            nameArray = scanner.nextLine().split(",");
            trimWhiteSpace(nameArray);
        } while (!checkNamesValid(nameArray));
        print("");
        return makeArrayToArrayList(nameArray);
    }

    private boolean checkNamesValid(String[] nameArray) {
        if (checkNamesCountValid(nameArray)
                && checkNamesLengthValid(nameArray)
                && !checkNamesDuplicated(makeArrayToArrayList(nameArray))) {
            return true;
        }
        return false;
    }

    private ArrayList<String> makeArrayToArrayList(String[] input) {
        return new ArrayList<>(Arrays.asList(input));
    }

    private void trimWhiteSpace(String[] nameArray) {
        for (int i = 0; i < nameArray.length; i++) {
            nameArray[i] = nameArray[i].trim();
        }
    }

    private boolean checkNamesCountValid(String[] nameArray) {
        if (nameArray.length < MINIMUM_NAME_COUNT) {
            print(ErrorMessage.PLAYER_IS_NOT_ENTERED);
            return false;
        }
        return true;
    }

    private boolean checkNamesLengthValid(String[] nameArray) {
        boolean isValid = true;

        for (String string : nameArray) {
            isValid &= checkNameLengthValid(string);
        }
        if (!isValid) {
            print(ErrorMessage.PLAYER_NAME_IS_TOO_SHORT);
        }
        return isValid;
    }

    private boolean checkNameLengthValid(String input) {
        if (input.length() < MINIMUM_NAME_LENGTH) {
            return false;
        }
        return true;
    }

    private boolean checkNamesDuplicated(ArrayList<String> names) {
        boolean isDuplicated = false;
        ArrayList<String> newList = new ArrayList<>();

        for (String name : names) {
            isDuplicated |= listContainsString(newList, name);
            newList.add(name);
        }
        if (isDuplicated) {
            print(ErrorMessage.PLAYER_NAME_IS_DUPLICATED);
        }
        return isDuplicated;
    }

    private boolean listContainsString(ArrayList<String> list, String string) {
        if (list.contains(string)) {
            return true;
        }
        return false;
    }

    public double getPlayerBattingMoney(String playerName) {
        String battingMoneyString;

        do {
            print(Message.makeMessageAskPlayerBattingAmout(playerName));
            battingMoneyString = scanner.nextLine().trim();
        } while (!checkBattingMoneyValid(battingMoneyString));

        print("");
        return Double.parseDouble(battingMoneyString);
    }

    private boolean checkBattingMoneyValid(String battingMoneyString) {
        if (!checkBattingMoneyContainsChar(battingMoneyString)
                && !checkBattingMoneyIsMinus(battingMoneyString)
                && !checkBattingMoneyIsZero(battingMoneyString)) {
            return true;
        }
        return false;
    }

    private boolean checkBattingMoneyContainsChar(String battingMoneyString) {
        try {
            Integer.parseInt(battingMoneyString);
        } catch (NumberFormatException e) {
            print(ErrorMessage.PLAYER_BATTING_MONEY_CONTAINS_CHAR);
            return true;
        }
        return false;
    }

    private boolean checkBattingMoneyIsMinus(String battingMoneyString) {
        if (Integer.parseInt(battingMoneyString) < 0) {
            print(ErrorMessage.PLAYER_BATTING_MONEY_IS_MINUS);
            return true;
        }
        return false;
    }

    private boolean checkBattingMoneyIsZero(String battingMoneyString) {
        if (Integer.parseInt(battingMoneyString) == 0) {
            print(ErrorMessage.PLAYER_BATTING_MONEY_IS_ZERO);
            return true;
        }
        return false;
    }

    public boolean getPlayerChoiceDrawOneMoreOrNot(Player player) {
        String playerChoice;

        do {
            print(Message.makeMessagePlayerWantMoreCard(player));
            playerChoice = scanner.nextLine().trim();
        } while (!checkPlayerInputIsYesOrNo(playerChoice));
        if (playerChoice.equals(YES)) {
            return true;
        }
        return false;
    }

    private boolean checkPlayerInputIsYesOrNo(String playerChoice) {
        if (playerChoice.equals(YES) || playerChoice.equals(NO)) {
            return true;
        }
        print(ErrorMessage.INPUT_IS_NOT_YES_OR_NO);
        return false;
    }

    private void print(String message) {
        System.out.println(message);
    }
}
