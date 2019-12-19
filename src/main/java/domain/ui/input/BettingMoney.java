package domain.ui.input;

import java.util.Scanner;
import java.util.regex.Pattern;

public class BettingMoney {

    public static double input(String name) {
        Scanner scanner = new Scanner(System.in);
        String inputString;
        do {
            System.out.printf("%s의 배팅 금액은?\n", name);
            inputString = scanner.nextLine();
        } while (!isNumberValid(inputString));
        return Integer.parseInt(inputString);
    }

    private static boolean isNumberValid(String inputString) {
        final int minimumBettingMoney = 0;
        if (!(isNumeric(inputString) && Integer.parseInt(inputString) > minimumBettingMoney)) {
            System.out.printf("%d 보다 큰 '숫자'를 입력해주세요.", minimumBettingMoney);
            return false;
        }
        return true;
    }

    private static boolean isNumeric(String inputString) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (inputString == null) {
            return false;
        }
        return pattern.matcher(inputString).matches();
    }
}
