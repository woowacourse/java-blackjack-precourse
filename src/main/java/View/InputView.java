package View;

import domain.user.User;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람들의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_BETTING_MONEY_MESSAGE = "의 배팅 금액은?";
    private static final String NOT_A_NUMBER_MESSAGE = "숫자만 입력해주세요.";
    private static final String INPUT_IF_GET_CARD_MESSAGE = "님 카드를 1장 더 받으시겠습니까?";

    private InputView() {}

    public static String inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES_MESSAGE);
        return scanner.nextLine();
    }

    public static double inputBettingMoney(String playerName) {
        try {
            System.out.println(playerName + INPUT_BETTING_MONEY_MESSAGE);
            return scanner.nextDouble();
        } catch (NumberFormatException e) {
            System.out.println(NOT_A_NUMBER_MESSAGE);
            return inputBettingMoney(playerName);
        }
    }

    public static String inputIfGetCard(User user) {
        System.out.println(user.getName() + INPUT_IF_GET_CARD_MESSAGE);
        return scanner.nextLine();
    }

}