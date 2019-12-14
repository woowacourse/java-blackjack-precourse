package domain;

import java.util.Scanner;

/**
 * 게임에 필요한 입,출력을 담당하는 객체
 */
public class GamePrinter {
    Scanner scanner = new Scanner(System.in);

    void getPlayerNameFromUser() {
        String playerNameFromUser;
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            playerNameFromUser = scanner.nextLine();
        } while (!checkPlayerName(playerNameFromUser));
        String[] splitedPlyerName = splitPlayerName(playerNameFromUser);
    }

    String[] splitPlayerName(String playerNameFromUser) {
        String[] splitedPlayerName = playerNameFromUser.split(",");
        return splitedPlayerName;
    }

    Boolean checkPlayerName(String playerNameFromUser) {
        if (playerNameFromUser.length() == 0
                || playerNameFromUser.charAt(playerNameFromUser.length() - 1) == ',') {
            System.out.println("이름을 잘못 입력했습니다.");
            return false;
        }
        return true;
    }
}
