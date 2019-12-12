package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer implements User {
    private static final String DEALER_NAME = "딜러";

    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
