package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class Gamer {
    private final List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

}
