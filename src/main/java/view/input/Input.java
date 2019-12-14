package view.input;

import domain.manager.DoubleConverter;
import view.output.Output;

import java.util.Scanner;

public class Input {
    private Scanner scanner = new Scanner(System.in);

    public String inputName() {
        return scanner.nextLine();
    }

    public Double inputMoney() {
        return new DoubleConverter().stringToDouble(scanner.nextLine());
    }

    public String inputChoice() {
        return scanner.nextLine();
    }

    public boolean oneMoreCard(String choice) {
        if (choice.equals("y") || choice.equals("Y")) {
            return true;
        }

        if (choice.equals("n") || choice.equals("N")) {
            return false;
        }

        new Output().showMessageMisInputErrorClose();

        return false;
    }
}
