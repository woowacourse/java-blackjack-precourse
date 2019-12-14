package domain.user;

import domain.card.Card;
import domain.card.CardBundle;

public abstract class Gamer {

    private final CardBundle cards = new CardBundle();

    public void addCard(Card card) {
        this.cards.addCard(card);
    }

    public int getScore() {
        return cards.getCardSum();
    }

    public abstract boolean canReceive();
}