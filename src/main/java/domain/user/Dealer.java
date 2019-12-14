package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Participant {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
        super("딜러");
        super.cards  = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
