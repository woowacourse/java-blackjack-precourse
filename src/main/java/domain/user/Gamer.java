package domain.user;

import domain.card.Card;
import domain.card.CardBundle;

import java.util.List;

public abstract class Gamer {

    private final CardBundle cards = new CardBundle();

    public void addCard(Card card) {
        this.cards.addCard(card);
    }

    public int getScore() {
        return cards.getCardSum();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public abstract String getName();

    public abstract boolean canReceive();
}