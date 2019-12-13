package domain.manager;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
}
