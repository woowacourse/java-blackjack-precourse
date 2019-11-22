package domain;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = CardFactory.create();
    }

    // TODO 기능 구현
}
