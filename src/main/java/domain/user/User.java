package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public String toString() {
        List<String> stringCardList = cards.stream()
                .map(c -> c.toString())
                .collect(Collectors.toList());
        return String.join(", ", stringCardList);
    }
}
