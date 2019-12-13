package view;

import java.util.Scanner;

public class Input {
    private static final String COMMA = ",";

    Scanner scanner = new Scanner(System.in);

    public Input() {
    }

    public String input() {
        return scanner.nextLine();
    }
}
