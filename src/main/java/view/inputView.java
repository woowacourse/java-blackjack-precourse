package view;

import java.util.Scanner;

public class inputView {
    private static Scanner scanner;

    public static String inputPlayerNames() {
        scanner = new Scanner(System.in);
        System.out.println("게임에 참여할 사람의 이름을 입력하세요");
        String playersName = scanner.nextLine();

        return playersName;
    }

    public static String inputPlayerBet(String playerName) {
        scanner = new Scanner(System.in);
        System.out.println(playerName + "의 베팅 금액은?");
        String playersName = scanner.nextLine();

        return playersName;
    }
}
