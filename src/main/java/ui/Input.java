package ui;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Input {
    private static final String YES_RESPONSE = "y";
    private static final String NO_RESPONSE = "n";
    private static final String NAME_DIVIDER = ",";
    private static final int MAX_PLAYER = 8;

    private Scanner scanner = new Scanner(System.in);

    private String userResponse() {
        return scanner.nextLine();
    }

    public List<String> getPlayerEntry() {
        Output.displayForGetPlayerNames();
        return Optional.of(userResponse())
                .filter(this::isNotEmptyInput)
                .filter(this::isNotEmptyName)
                .filter(this::isNotEndWithDivider)
                .filter(this::possiblePlayerNumbers)
                .map(this::nameSpliter)
                .orElseGet(this::getPlayerEntry);
    }

    public double getBettingMoney(String playerName) {
        Output.displayForGetPlayerBettingMoney(playerName);
        return Optional.of(userResponse())
                .flatMap(this::stringToDoubleForMoney)
                .orElseGet(() -> getBettingMoney(playerName));
    }

    public boolean pickExtraCard(String playerName) {
        Output.displayForAskPickMoreCard(playerName);
        return Optional.of(userResponse())
                .filter(this::isCorrectYesOrNo)
                .map(x -> x.equals(YES_RESPONSE))
                .orElseGet(() -> pickExtraCard(playerName));
    }

    private boolean isNotEmptyInput(String playerNames) {
        return !playerNames.equals("");
    }

    private boolean isNotEmptyName(String playerNames) {
        return !playerNames.contains(NAME_DIVIDER + NAME_DIVIDER);
    }

    private boolean isNotEndWithDivider(String playerNames) {
        return !playerNames.endsWith(NAME_DIVIDER);
    }

    private List<String> nameSpliter(String playerNames) {
        return Arrays.asList(playerNames.split(NAME_DIVIDER));
    }

    private boolean possiblePlayerNumbers(String playerNames) {
        return playerNames.chars().mapToObj(x -> (char)x)
                .map(String::valueOf)
                .filter(x -> x.equals(NAME_DIVIDER))
                .count() < MAX_PLAYER;
    }

    private Optional<Double> stringToDoubleForMoney(String bettingMoney) {
        try {
            return Optional.of(Double.parseDouble(bettingMoney));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private boolean isCorrectYesOrNo(String response) {
        return response.equals(YES_RESPONSE)
                || response.equals(NO_RESPONSE);
    }
}
