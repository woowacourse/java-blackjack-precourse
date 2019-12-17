package domain.Game;

import java.util.Arrays;
import java.util.Scanner;

public class Input {
    Scanner scanner = new Scanner(System.in);

    public String[] playerNameInput() {
        String playerNamesInput;
        String[] playerNames;
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            System.out.println("입력 조건 : 1. 영대소문자만 가능 2. 공백 사용 불가");
            playerNamesInput = scanner.nextLine();
            playerNames = playerNamesInput.split(",");
        } while (!playerNameInputConfirm(playerNames, playerNamesInput));
        return playerNames;
    }

    private boolean playerNameInputConfirm(String[] playerNames, String playerNamesInput) {
        if (playerNamesInput.endsWith(",")) {
            return false;
        }
        return Arrays.stream(playerNames).allMatch(playerName -> playerName.matches("^[a-zA-Z]*$") &&
                !playerName.isEmpty());
    }
}
