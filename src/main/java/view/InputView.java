package view;

import java.util.Scanner;

import domain.user.Player;
import utils.InputValidator;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String GET_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String GET_BETTING_MONEY_MESSAGE = "의 배팅 금액은?";
    private static final String GET_PLAYER_INTENT_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    public static String playerNames() {
        System.out.println(GET_PLAYER_NAMES_MESSAGE);
        return scanner.nextLine();
    }

    public static int bettingMoney(String playerName) {
        System.out.println(playerName + GET_BETTING_MONEY_MESSAGE);
        int inputMoney = Integer.parseInt(scanner.nextLine());
        if (!InputValidator.isValidNumber(inputMoney)) {
            throw new IllegalArgumentException("잘못된 배팅 금액 입력입니다.");
        }
        return inputMoney;
    }

    public static String playerIntent(Player player) {
        System.out.println(player.getName() + GET_PLAYER_INTENT_MESSAGE);
        String inputIntent = scanner.nextLine();
        if (!InputValidator.isValidIntent(inputIntent)) {
            throw new IllegalArgumentException("y 또는 n 이외의 입력입니다.");
        }
        return inputIntent;
    }
}
