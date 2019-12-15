package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final List<Card> cards = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }


    public String getName() {
        return this.name;
    }

    public List<Card> getCards() {
        return this.cards;
    }
}
