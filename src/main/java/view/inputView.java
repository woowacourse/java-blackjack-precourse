package view;

import java.util.Scanner;

public class inputView {
    private static Scanner scanner;

    public static String inputPlayerNames() {
        scanner = new Scanner(System.in);
        System.out.println("게임에 참여할 사람의 이름을 입력하세요");
        String playersName = scanner.nextLine();

        return playersName;
    }
}
