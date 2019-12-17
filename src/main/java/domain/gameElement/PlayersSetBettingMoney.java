package domain.gameElement;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PlayersSetBettingMoney {
    private final int digitsLimit = 13; // 1조 단위 미만만 허용
    private Scanner scanner = new Scanner(System.in);

    public double playerSetBettingMoney(String playerName) {
        String bettingMoney = "";
        PlayersSetBettingMoney playerBettingMoneyChecker = new PlayersSetBettingMoney();
        do {
            System.out.println("\n" + playerName + "의 배팅 금액은? (1원 단위로 쉼표없이 입력해주세요, 1조 미만으로만 가능합니다.)");
            bettingMoney = scanner.nextLine().trim();
        } while (!playerBettingMoneyChecker.playerBettingMoneyCheck(bettingMoney));
        return Double.parseDouble(bettingMoney);
    }

    public boolean playerBettingMoneyCheck(String bettingMoney) {
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
        if (checkBlankSet.contains(true) && bettingMoney.length() < digitsLimit) {
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
        if (bettingMoney <= 0) {
            return false;
        }
        return true;
    }
}
