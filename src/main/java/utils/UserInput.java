package utils;

import java.util.*;

public class UserInput {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(최대 8명, 쉼표 기준으로 분리)");
        String playersName = SCANNER.nextLine();
        if(playersName.equals("")) {
            throw new IllegalArgumentException("이름은 비워둘 수 없습니다!");
        }
        return splitPlayersName(playersName);
    }

    private static List<String> splitPlayersName(String playerString) {
        List<String> playersName = Arrays.asList(playerString.trim().replace(" ", "").split(","));
        if(playersName.contains("")) {
            throw new IllegalArgumentException("입력받은 이름 중 공백이 있습니다!\n");
        }
        if(playersName.size() < 2 || playersName.size() > 8) {
            throw new IllegalArgumentException("최소 2명 최대 8명의 플레이어여야 합니다.\n");
        }
        return playersName;
    }

    public static int inputBettingMoney() {
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