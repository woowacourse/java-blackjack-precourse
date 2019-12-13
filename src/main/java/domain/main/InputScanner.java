package domain.main;

import java.util.Scanner;

public class InputScanner {

    private static Scanner scanner = new Scanner(System.in);

    public String stringLine() {
        return scanner.nextLine();
    }

    public String string() {
        return scanner.next();
    }

    public int integer() {
        return scanner.nextInt();
    }

}
