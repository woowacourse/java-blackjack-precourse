package gameInput;

import domain.user.Player;

import java.util.Scanner;

public class GameInputScanner {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String NAME_INPUT_STATEMENT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String BATTING_INPUT_FORMATTED_STATEMENT = "%s의 배팅 금액은?";
    private static final String ADD_ONEMORE_CARD_STATEMENT = "%s는 한장의 카드를 더 받겠습니까?";

    public String askNamesFromUser() {
        System.out.println(NAME_INPUT_STATEMENT);
        return scanner.nextLine();
    }

    public String askBattingMoney(String name) {
        System.out.println(String.format(BATTING_INPUT_FORMATTED_STATEMENT, name));
        return scanner.nextLine();
    }

    public String askAddOneMoreCard(Player player) {
        System.out.println(String.format(ADD_ONEMORE_CARD_STATEMENT, player.getName()));
        return scanner.nextLine();
    }
}
