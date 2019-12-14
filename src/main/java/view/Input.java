package view;


import java.util.*;

public class Input {
    private static final String COMMA = ",";
    private static final String Y = "y";
    private static final String N = "n";
    private static final int MAX_PLAYER = 10;

    private Scanner scanner = new Scanner(System.in);

    public Input() {
    }

    private String input() {
        return scanner.nextLine();
    }

    public List<String> asGamers() {
        Output.showGamePlayerInput();
        return Optional.of(input())
                .filter(this::notContainsDoubleComma)
                .filter(this::notEndWithComma)
                .filter(this::noMoreThenTenPlayers)
                .map(this::splitAsComma)
                .orElseGet(this::asGamers);
    }

    public double asBettingMoney(String playerName) {
        Output.showBettingMoneyInput(playerName);
        return Optional.of(input())
                .flatMap(this::stringToDouble)
                .orElseGet(() -> asBettingMoney(playerName));
    }

    public boolean asWantMoreCard(String playerName) {
        Output.showWantMoreCard(playerName);
        return Optional.of(input())
                .filter(this::isYesOrNo)
                .map(this::parseToBoolean)
                .orElseGet(() -> asWantMoreCard(playerName));
    }

    private boolean isYesOrNo(String input) {
        return input.equals(Y) || input.equals(N);
    }

    private boolean parseToBoolean(String input) {
        return input.equals(Y);
    }

    private Optional<Double> stringToDouble(String input) {
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

    private boolean noMoreThenTenPlayers(String input) {
        return input.chars().mapToObj(x -> (char) x)
                .map(String::valueOf)
                .filter(x -> x.equals(COMMA))
                .count() < MAX_PLAYER;
    }

    public List<String> splitAsComma(String input) {
        return Arrays.asList(input.split(COMMA));
    }
}
