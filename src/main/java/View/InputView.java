package View;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람들의 이름을 입력하세요.(쉼표 기준으로 분리)";

    private InputView() {}

    public static String inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES_MESSAGE);
        return scanner.nextLine();
    }
}