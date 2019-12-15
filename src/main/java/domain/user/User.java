package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final List<Card> cards = new ArrayList<>();

    public User() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

}
