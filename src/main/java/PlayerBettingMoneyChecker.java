import java.util.HashSet;
import java.util.Set;

public class PlayerBettingMoneyChecker {
    boolean playerBettingMoneyCheck(String bettingMoney) {
        if (bettingMoney.isEmpty()) {
            return false;
        }
        if (!bettingMoneyIsNumber(bettingMoney)) {
            return false;
        }
        if (!bettingMoneyCheckValue(Integer.parseInt(bettingMoney))) {
            return false;
        }
        return true;
    }

    private boolean bettingMoneyIsNumber(String bettingMoney) {
        Set<Boolean> checkBlankSet = new HashSet<Boolean>();
        for (int i = 0; i < bettingMoney.length(); i++) {
            checkBlankSet.add(charIsNumber(bettingMoney.charAt(i)));
        }
        if (checkBlankSet.contains(true)) {
            return true;
        }
        return false;
    }

    private boolean charIsNumber(char number) {
        if (number >= '0' || number <= '9') {
            return true;
        }
        return false;
    }

    private boolean bettingMoneyCheckValue(int bettingMoney) {
        if (bettingMoney < 0 || bettingMoney > Integer.MAX_VALUE) {
            return false;
        } else if (bettingMoney % 10 != 0) {
            return false;
        }
        return false;
    }
}
