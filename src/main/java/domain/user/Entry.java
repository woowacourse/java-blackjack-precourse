package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Entry {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void showAllCard() {
        cards.forEach(card -> System.out.print(card.toString() + " "));
    }
}