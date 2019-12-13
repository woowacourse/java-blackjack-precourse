package domain.main;

import java.util.Scanner;

public class InputScanner {

    private static Scanner scanner = new Scanner(System.in);

    public String getStringLine() {
        return scanner.nextLine();
    }

    public String getString() {
        return scanner.next();
    }

    public int getInteger() {
        return scanner.nextInt();
    }

    public Double getDouble() {
        return scanner.nextDouble();
    }

}
