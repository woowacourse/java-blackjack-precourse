package view;

import domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class CardStatus {
    private static final String JOIN_DELIMITER = ", ";

    public static String getStatus(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getSymbol() + card.getType())
                .collect(Collectors.joining(JOIN_DELIMITER));
    }
}
