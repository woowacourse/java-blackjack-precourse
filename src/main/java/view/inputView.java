package view;

import java.util.Scanner;

public class inputView {
    public static final String MESSAGE_INPUT_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요";
    public static final String MESSAGE_INPUT_BETTING_MONEY = "의 베팅 금액은?";
    public static final String MESSAGE_INPUT_Y_OR_N = "은(는) 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static Scanner scanner;

    public static String inputPlayerNames() {
        scanner = new Scanner(System.in);
        System.out.println(MESSAGE_INPUT_PLAYER_NAME);

        return scanner.nextLine();
    }

    public static String inputPlayerBet(String playerName) {
        scanner = new Scanner(System.in);
        System.out.println(playerName + MESSAGE_INPUT_BETTING_MONEY);

        return scanner.nextLine();
    }

    public static String inputHitOrStand(String playerName) {
        scanner = new Scanner(System.in);
        System.out.println(playerName + MESSAGE_INPUT_Y_OR_N);
        String response = scanner.nextLine();

        return response.toLowerCase();
    }
}
