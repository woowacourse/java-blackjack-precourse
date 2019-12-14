package domain;

import java.util.Scanner;

/**
 * 게임에 필요한 입,출력을 담당하는 객체
 */
public class GamePrinter {
    Scanner scanner = new Scanner(System.in);

    void getPlayerNameFromUser(){
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리");
        String playerNameFromUser = scanner.nextLine();
    }
}
