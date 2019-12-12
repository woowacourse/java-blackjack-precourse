package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInput {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return splitPlayersName(SCANNER.nextLine());
    }

    private static List<String> splitPlayersName(String playerString) {
        return Arrays.asList(playerString.trim()
                .replace(" ", "").split(","));
    }

    public static int inputBettingMoney(String name) {
        System.out.println(name+"의 배팅 금액은?");
        return SCANNER.nextInt();
    }

    public static Boolean inputHit() {
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