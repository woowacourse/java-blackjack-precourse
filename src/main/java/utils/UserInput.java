package utils;

import java.util.Scanner;

/**
 * 실제 유저의 입력을 받는 객체 
 */

public class UserInput {
    public static String input() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        return string;
    }
}
