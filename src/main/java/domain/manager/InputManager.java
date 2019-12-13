package domain.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputManager {
    final static Scanner scanner = new Scanner(System.in);
    static List<String> playerNameList;

    public static List<String> getPlayerNameList() {
        Scanner scanner = new Scanner(System.in);
        String inputString;
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            inputString = scanner.nextLine();
            playerNameList = Arrays.asList(inputString.split(","));
        } while (!isNameValid(inputString));
        return playerNameList;
    }

    private static boolean isNameValid(String inputString) {
        final int validDifference = 1;
        int delimiterCount = 0;

        for (int i = 0; i < inputString.length(); i++) {
            delimiterCount = updateDelimiterCount(delimiterCount, inputString.charAt(i));
        }
        return (playerNameList.size() - delimiterCount) == validDifference;
    }

    private static int updateDelimiterCount(int delimiterCount, char inputCharacter) {
        final char delimiter = ',';
        if (inputCharacter == delimiter) {
            delimiterCount++;
        }
        return delimiterCount;
    }

    public static List<Integer> getBettingMoneyList() {
        List<Integer> bettingMoneyList = new ArrayList<Integer>();

        for (int i = 0; i < playerNameList.size(); i++) {
            bettingMoneyList.add(getBettingMoney(i));
        }
        return bettingMoneyList;
    }

    private static int getBettingMoney(int i) {
        String inputString;

        do {
            System.out.printf("%s의 배팅 금액은?\n", playerNameList.get(i));
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
