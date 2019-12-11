package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String GET_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    public static String playerNames() {
        System.out.println(GET_PLAYER_NAMES_MESSAGE);
        return scanner.nextLine();
    }
}
