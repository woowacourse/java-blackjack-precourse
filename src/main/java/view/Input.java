package view;

import domain.user.Player;

import java.util.*;

public class Input {
    private static final String COMMA = ",";
    private static final String Y = "y";
    private static final String N = "n";

    private Scanner scanner = new Scanner(System.in);

    public Input() {
    }

    public String input() {
        return scanner.nextLine();
    }

    public List<String> asGamers() {
        Output.showGamePlayerInput();
        return Optional.of(input())
                .filter(this::notContainsDoubleComma)
                .filter(this::notEndWithComma)
                .map(this::splitAsComma)
                .orElseGet(this::asGamers);
    }

    public double asBettingMoney(String playerName) {
        Output.showBettingMoneyInput(playerName);
        return Optional.of(input())
                .flatMap(this::stringToDouble)
                .orElseGet(() -> asBettingMoney(playerName));
    }

    public boolean asWantMoreCard() {
        return Optional.of(input())
                .filter(this::isYesOrNo)
                .map(this::parseToBoolean)
                .orElseGet(this::asWantMoreCard);
    }

    public boolean isYesOrNo(String input) {
        return input.equals(Y) || input.equals(N);
    }

    public boolean parseToBoolean(String input) {
        return input.equals(Y);
    }

    public Optional<Double> stringToDouble(String input) {
        try {
            return Optional.of(Double.parseDouble(input));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public boolean notContainsDoubleComma(String input) {
        return !input.contains(COMMA + COMMA);
    }

    public boolean notEndWithComma(String input) {
        return !input.endsWith(COMMA);
    }

    public List<String> splitAsComma(String input) {
        return Arrays.asList(input.split(COMMA));
    }
}
