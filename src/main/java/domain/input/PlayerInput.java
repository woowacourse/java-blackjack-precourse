package domain.input;


import java.util.Scanner;

public class PlayerInput {
    Scanner scanner;

    public PlayerInput() {
        scanner = new Scanner(System.in);
    }

    public String inputPlayerNames() {
        return scanner.nextLine();
    }

}
