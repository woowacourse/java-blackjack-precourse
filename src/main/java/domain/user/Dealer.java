package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

}
