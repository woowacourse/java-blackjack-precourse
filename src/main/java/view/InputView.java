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
        int money = Integer.parseInt(scanner.nextLine());
        InputValidator.checkMoneyValidity(money);
        return money;
    }

    public static String playerIntent(Player player) {
        System.out.println(player.getName() + GET_PLAYER_INTENT_MESSAGE);
        String intent = scanner.nextLine();
        InputValidator.checkIntentValidity(intent);
        return intent;
    }
}
