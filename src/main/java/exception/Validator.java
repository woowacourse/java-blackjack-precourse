package exception;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.user.Player;

public class Validator {
    private static final int MIN_BETTING_MONEY = 1;
    private static final int MIN_PLAYERS_SIZE = 2;
    private static final int MAX_PLAYERS_SIZE = 8;
    private static final String COMMA = ",";
    private static final String BLANK = " ";
    private static final String YES = "y";
    private static final String NO = "n";

    public static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throwException(Exceptions.NOT_INTEGER);
        }
    }
    public static void validateYOrN(String input) {
        if(!(input.equals(YES) || input.equals(NO))) {
            throwException(Exceptions.NOT_YES_OR_NO);
        }
    }

    public static void validatePlayersSize(int size) {
        if (size < MIN_PLAYERS_SIZE || size > MAX_PLAYERS_SIZE) {
            throwException(Exceptions.WRONG_PLAYERS_SIZE);
        }
    }

    public static void validateDuplicatedName(List<Player> players) {
        Set<String> noneDuplicatedNames = new HashSet<>();
        for (Player p : players) {
            noneDuplicatedNames.add(p.getName());
        }
        if (players.size() != noneDuplicatedNames.size()) {
            throwException(Exceptions.DUPLICATED_PLAYERS_NAME);
        }
    }

    public static void validateCardSizeAndPlayerSize(int cardSize, int playerSize) {
        if (cardSize != playerSize) {
            throwException(Exceptions.NOT_MATCH_SIZE);
        }
    }

    public static void checkPlayerNames(String[] names) {
        for (String name : names) {
            checkPlayerName(name);
        }
    }

    public static void validateBettingMoney(double battingMoney) {
        if(battingMoney < MIN_BETTING_MONEY) {
            throwException(Exceptions.WRONG_BETTING_MONEY);
        }
    }

    public static void checkPlayerName(String name) {
        validateEmpty(name);
        validateContainsBlank(name);
        validateContainsComma(name);
    }
    private static void validateEmpty(String name) {
        if (name.isEmpty()) {
            throwException(Exceptions.EMPTY_PLAYER_NAME);
        }
    }

    private static void validateContainsBlank(String name) {
        if (name.contains(BLANK)) {
            throwException(Exceptions.CONTAINS_BLANK);
        }
    }

    private static void validateContainsComma(String name) {
        if (name.contains(COMMA)) {
            throwException(Exceptions.CONTAINS_COMMA);
        }
    }

    private static void throwException(Exceptions e) {
        throw new IllegalArgumentException(e.getContent());
    }
}
