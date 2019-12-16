package domain.user;

import java.util.ArrayList;

import domain.card.Card;

public class User {
    private static final String DEALER_NAME = "딜러 ";

    final ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> showCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return DEALER_NAME;
    }
}
