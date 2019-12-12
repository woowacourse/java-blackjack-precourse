package view.input;

import java.util.Scanner;

public class Input {
    Scanner scanner = new Scanner(System.in);

    public String inputName() {
        return scanner.nextLine();
    }

    public Double inputMoney() {
        return scanner.nextDouble();
    }
}
