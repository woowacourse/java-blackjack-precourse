package ui;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Input {
    private static final String YES_RESPONSE = "y";
    private static final String NO_RESPONSE = "n";
    private static final String NAME_DIVIDER = ",";
    private static final int MIN_PLAYER = 2;
    private static final int MAX_PLAYER = 8;

    private Scanner scanner = new Scanner(System.in);

    private String userResponse() {
        return scanner.nextLine();
    }

    public List<String> getPlayerEntry() {
        Output.displayForGetPlayerNames();
        return Optional.of(userResponse())
                .filter(this::isNotEmptyName)
                .filter(this::isNotEndWithDivider)
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

    public boolean isNotEmptyName(String playerNames) {
        return !playerNames.contains(NAME_DIVIDER + NAME_DIVIDER);
    }

    public boolean isNotEndWithDivider(String playerNames) {
        return !playerNames.endsWith(NAME_DIVIDER);
    }

    public List<String> nameSpliter(String playerNames) {
        return Arrays.asList(playerNames.split(NAME_DIVIDER));
    }

    public Optional<Double> stringToDoubleForMoney(String bettingMoney) {
        try {
            return Optional.of(Double.parseDouble(bettingMoney));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public boolean isCorrectYesOrNo(String response) {
        return response.equals(YES_RESPONSE)
                || response.equals(NO_RESPONSE);
    }

    public boolean responseChange(String response) {
        return response.equals(YES_RESPONSE);
    }
}
