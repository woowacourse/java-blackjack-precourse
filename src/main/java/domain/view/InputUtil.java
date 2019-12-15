package domain.view;

import java.util.Scanner;

public class InputUtil {
    static final Scanner SCANNER = new Scanner(System.in);

    public static String[] inputName() {
        return SCANNER.nextLine().replace(" ", "").split(",");
    }

    public static int inputBettingMoney() {
        return SCANNER.nextInt();
    }
}
