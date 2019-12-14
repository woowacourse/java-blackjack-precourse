package domain.dispenser;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.List;

public abstract class CardDispenser {
    protected final List<Card> cards = CardFactory.create();

    public abstract Card pick();
}
