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

    public boolean oneMoreCard() {
        String answer = scanner.nextLine();

        if (answer.equals("y") || answer.equals("Y")) {
            return true;
        }
        if (answer.equals("n") || answer.equals("N")) {
            return false;
        }
        new Output().showMessageMisInputErrorClose();

        return false;
    }
}
