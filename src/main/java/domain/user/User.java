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

    public void printCards() {
    }

    public String getCardValue() {
        ArrayList<String> cardInfo = new ArrayList<>();
        for (Card card : this.cards) {
            cardInfo.add(card.printSymbolAndNumber());
        }
        return String.join(", ", cardInfo);
    }
}
