package domain.card;

import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards() {
        this.cards = CardFactory.create();
    }

    public Card getCardByIndex(int index) {
        return cards.get(index);
    }

}
