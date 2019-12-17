package view;

import domain.user.Player;
import exception.Validator;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String BUTTING_MONEY = "의 베팅 금액은?";
    private static final String ONE_MORE = "님은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    public static String inputPlayersName() {
        OutputView.println(PLAYERS_NAME);
        return scan();
    }

    public static int inputBettingMoney(String playerName) {
        OutputView.println(playerName + BUTTING_MONEY);
        String input = scan();
        try {
            Validator.validateInteger(input);
        } catch (IllegalArgumentException e) {
            OutputView.println(e.getMessage());
            return inputBettingMoney(playerName);
        }
        return Integer.parseInt(input);
    }

    public static String inputCheckHit(Player player) {
        OutputView.println(player.getName() + ONE_MORE);
        String input = scan();
        try {
            Validator.validateYOrN(input);
        } catch (IllegalArgumentException e) {
            OutputView.println(e.getMessage());
            return inputCheckHit(player);
        }
        return input;
    }

    private static String scan() {
        return scanner.nextLine();
    }
}
