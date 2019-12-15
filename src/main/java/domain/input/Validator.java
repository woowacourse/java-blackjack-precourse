package domain.input;

import java.util.Iterator;
import java.util.List;

/**
 * Validator
 *
 * @author hyochan
 * @version 0.0.1
 * @since 2019-12-14
 */

public class Validator {
    public static final int PLAYER_NAME_MAX_SIZE = 10;

    public boolean isValidName(List<String> input) {
        boolean validation = true;
        Iterator itr = input.iterator();
        while (itr.hasNext() && validation) {
            String playerName = (String) itr.next();
            validation = isValidName(playerName);
        }
        return validation;
    }

    public boolean isValidName(String input) {
        if (hasBlank(input) || isOverSize(input) || isNull(input) || input.length() == 0) {
            return false;
        }
        return true;
    }

    public boolean isValidYesNoInput(String input) {
        if (input.equals("y") || input.equals("n")) {
            return true;
        }
        return false;
    }

    public boolean isValidBettingAmount(int input) {
        return !(isNull(input) || !isPositiveNumber(input));
    }

    public boolean hasBlank(String name) {
        return name.contains(" ");
    }

    public boolean isNull(Object input) {
        return input == null;
    }

    public boolean isOverSize(String name) {
        return name.length() > PLAYER_NAME_MAX_SIZE;
    }

    public boolean isPositiveNumber(int num) {
        return num > 0;
    }
}
