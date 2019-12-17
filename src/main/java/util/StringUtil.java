package util;

import domain.card.Card;
import domain.user.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    private static final String DELIMITER = ",";

    public static List<String> getListFromString(String input) {
        return Arrays.asList(input.split(DELIMITER));
    }

    public static List<String> removeBlank(List<String> names) {
        return names.stream()
                .filter(name -> !name.isBlank())
                .collect(Collectors.toList());
    }

    public static String joinPlayerName(List<Player> input) {
        return input.stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    public static String joinCardName(List<Card> input) {
        return input.stream()
                .map(Card::toString)
                .collect(Collectors.joining(DELIMITER));
    }
}
