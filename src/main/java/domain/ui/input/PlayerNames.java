package domain.ui.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PlayerNames {

    private PlayerNames() {
    }

    public static List<String> input() {
        Scanner scanner = new Scanner(System.in);
        List<String> playerNames;
        String inputString;
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            inputString = scanner.nextLine();
            playerNames = Arrays.asList(inputString.split(","));
        } while (!isNameValid(inputString, playerNames.size()));
        return playerNames;
    }

    private static boolean isNameValid(String inputString, int nameCount) {
        final int validDifference = 1;
        int delimiterCount = 0;

        for (int i = 0; i < inputString.length(); i++) {
            delimiterCount = updateDelimiterCount(delimiterCount, inputString.charAt(i));
        }
        return (nameCount - delimiterCount) == validDifference;
    }

    private static int updateDelimiterCount(int delimiterCount, char inputCharacter) {
        final char delimiter = ',';
        if (inputCharacter == delimiter) {
            delimiterCount++;
        }
        return delimiterCount;
    }
}
