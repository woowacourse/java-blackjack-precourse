package domain.input;


import java.util.Scanner;

public class playerInput {
    Scanner scanner;

    public playerInput() {
        scanner = new Scanner(System.in);
    }

    public String inputPlayerNames() {
        return scanner.nextLine();
    }
}
