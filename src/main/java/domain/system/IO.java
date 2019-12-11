package domain.system;

import java.util.Scanner;

public class IO {
    private static final Scanner scan = new Scanner(System.in);

    private static final String INVALID_INPUT = null;

    public static String getNextLine() {
        return scan.nextLine();
    }

    public static String getPlayersNames() {
        String playersNamePattern = "([a-zA-Z][a-zA-Z]{0,9}(,|$))+";
        String question = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

        return getInputUntilValid(question, playersNamePattern);
    }

    public static int getPlayerBettingMoney(String name) {
        String bettingMoneyPattern = "^[1-9][0-9]*";
        String question = name + "의 배팅 금액은?";

        return Integer.parseInt(getInputUntilValid(question, bettingMoneyPattern));
    }

    private static String getInputUntilValid(String question, String validPattern) {
        String input = INVALID_INPUT;

        while (input != INVALID_INPUT) {
            input = getInput(question, validPattern);
        }
        return input;
    }

    private static String getInput(String question, String validPattern) {
        String input;

        System.out.println(question);
        input = scan.nextLine();
        if (!input.matches(validPattern)) {
            return INVALID_INPUT;
        }
        return input;
    }
}
