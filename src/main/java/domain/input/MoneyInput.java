package domain.input;

import java.util.Scanner;

public class MoneyInput {
    Scanner scanner;

    public MoneyInput() {
        scanner = new Scanner(System.in);
    }

    public double inputBettingMoney() {
        return scanner.nextDouble();
    }
}
