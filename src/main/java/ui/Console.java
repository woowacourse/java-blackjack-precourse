package ui;

import common.BlackjackConfig;
import domain.BlackjackPrinter;
import domain.UserInterface;
import domain.errors.InvalidInputException;
import domain.user.Will;

import java.util.Arrays;
import java.util.Scanner;

public class Console implements UserInterface {

    private Scanner scanner;
    private BlackjackPrinter blackjackPrinter;

    public Console(Scanner scanner, BlackjackPrinter blackjackPrinter) {
        this.scanner = scanner;
        this.blackjackPrinter = blackjackPrinter;
    }

    @Override
    public String[] extractNames() {
        blackjackPrinter.printRequestForNames();
        String input = scanner.nextLine();
        try {
            return parseNames(input);
        } catch (RuntimeException e) {
            blackjackPrinter.printError(e);
            return extractNames();
        }
    }

    private String[] parseNames(String input) {
        input = input.trim();
        if (input.startsWith(BlackjackConfig.STANDARD_FOR_PARSE) || input.endsWith(BlackjackConfig.STANDARD_FOR_PARSE)) {
            throw new InvalidInputException("구분자는 처음이나 마지막에 쓸 수 없습니다.");
        }
        String[] names = input.split(BlackjackConfig.STANDARD_FOR_PARSE);
        if (!isValidNames(names)) {
            throw new InvalidInputException("입력된 이름에 오류가 있습니다.");
        }

        return names;
    }

    private boolean isValidNames(String[] names) {
        if (names.length < BlackjackConfig.MIN_PLAYERS || BlackjackConfig.MAX_PLAYERS < names.length) {
            throw new InvalidInputException(String.format("참가자 수는 %d명 ~ %d명이어야 합니다.", BlackjackConfig.MIN_PLAYERS, BlackjackConfig.MAX_PLAYERS));
        }
        for (String name : names) {
            if (!isValidName(name)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidName(String name) {
        if (BlackjackConfig.MAX_NAME_LENGTH < name.length()) {
            throw new InvalidInputException(String.format("참가자 이름 길이는 %d이하여야 합니다.", BlackjackConfig.MAX_NAME_LENGTH));
        }

        if (Arrays.stream(BlackjackConfig.NOT_ALLOWED_IN_NAME).parallel().anyMatch(name::contains)) {
            throw new InvalidInputException(String.format("참가자 이름에 띄어쓰기 및 특수기호(%s)는 금지됩니다.", String.join(",", BlackjackConfig.NOT_ALLOWED_IN_NAME)));
        }

        return true;
    }

    @Override
    public double getBettingMoney(String name) {
        blackjackPrinter.printRequestForMoney(name);
        try {
            double bettingMoney = Double.parseDouble(scanner.nextLine());
            validateMoney(bettingMoney);
            return bettingMoney;
        } catch (RuntimeException e) {
            blackjackPrinter.printError(e);
            return getBettingMoney(name);
        }
    }

    private void validateMoney(double bettingMoney) {
        if (bettingMoney <= BlackjackConfig.MIN_MONEY) {
            throw new InvalidInputException(String.format("베팅 금액은 %f보다 커야 합니다.", BlackjackConfig.MIN_MONEY));
        }
    }

    @Override
    public String getWillForMoreCard() {

        try {
            String input = scanner.nextLine();
            return parseWill(input);
        } catch (RuntimeException e) {
            blackjackPrinter.printError(e);
            return getWillForMoreCard();
        }
    }

    private String parseWill(String input) {
        if (willContainsInput(input)) {
            return input;
        }
        throw new InvalidInputException(String.format("%s 이외의 값을 입력했습니다.", String.join(",", BlackjackConfig.ALLOWED_WILL)));
    }

    private boolean willContainsInput(String input) {
        return Arrays.stream(Will.values()).anyMatch(will -> will.getValue().equals(input));
    }
}
