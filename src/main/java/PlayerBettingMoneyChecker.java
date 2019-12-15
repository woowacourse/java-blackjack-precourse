import java.util.HashSet;
import java.util.Set;

public class PlayerBettingMoneyChecker {
    private final int digitsLimit = 10; //10억단위까지 허용

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
        if (checkBlankSet.contains(true) || bettingMoney.length() > digitsLimit) {
            return true;
        }
        return false;
    }

    private boolean charIsNumber(char number) {
        if (number >= '0' && number <= '9') {
            return true;
        }
        return false;
    }

    private boolean bettingMoneyCheckValue(int bettingMoney) {
        if (bettingMoney < 0) {
            System.out.println("1");
            return false;
        } else if (bettingMoney % 10 != 0) {
            System.out.println(2);
            return false;
        }
        return true;
    }
}
