package view;

import java.util.Scanner;

public class inputView {
    private static Scanner scanner;

    public static String inputPlayerNames() {
        scanner = new Scanner(System.in);
        System.out.println("게임에 참여할 사람의 이름을 입력하세요");

        return scanner.nextLine();
    }

    public static String inputPlayerBet(String playerName) {
        scanner = new Scanner(System.in);
        System.out.println(playerName + "의 베팅 금액은?");

        return scanner.nextLine();
    }

    public static String inputHitOrStand(String playerName) {
        scanner = new Scanner(System.in);
        System.out.println(playerName + "은(는) 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String response = scanner.nextLine();

        return response.toLowerCase();
    }
}
