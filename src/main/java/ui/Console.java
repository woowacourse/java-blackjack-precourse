package ui;

import common.BlackjackConfig;

import java.util.Scanner;

public class Console implements UserInterface {

    private Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String[] extractNames() {
        String input = scanner.nextLine();
        try {
            return parseNames(input);
        } catch (RuntimeException e) {

        }

        return new String[0];
    }

    private String[] parseNames(String input) {
        input = input.trim();

        String[] names = input.split(BlackjackConfig.STANDARD_FOR_PARSE);

        return names;
    }

    @Override
    public int getBettingMoney() {
        int bettingMoney = Integer.parseInt(scanner.nextLine());
        return bettingMoney;
    }
}
