package utils;

import java.util.*;

public class UserInput {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playersName = SCANNER.nextLine();
        if(playersName.equals("")) {
            throw new IllegalArgumentException("이름은 비워둘 수 없습니다!");
        }
        return splitPlayersName(playersName);
    }

    private static List<String> splitPlayersName(String playerString) {
        return Arrays.asList(playerString.trim()
                .replace(" ", "").split(","));
    }

    public static int inputBettingMoney(String name) {
        int bettingMoney = Integer.parseInt(SCANNER.nextLine());
        if(bettingMoney <= 0) {
            throw new IllegalArgumentException();
        }
        return bettingMoney;
    }

    public static Boolean inputYesNo() {
        char expression = SCANNER.next().charAt(0);
        if (expression == 'y' || expression == 'Y') {
            return true;
        }
        if (expression == 'n' || expression == 'N') {
            return false;
        }
        throw new IllegalArgumentException("다시 입력해 주세요.");
    }
}