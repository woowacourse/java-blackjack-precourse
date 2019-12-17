package utils;

import java.util.Scanner;

public class UserInput {
    public static String input() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        return string;
    }
}
