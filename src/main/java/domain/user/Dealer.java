package domain.user;

import domain.card.Card;
import domain.deck.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private static final int ONEMORE_CARD_BASIS = 16;

    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    private boolean isUnberSixteen() {
        return calculateSum() <= ONEMORE_CARD_BASIS;
    }

    public int calculateSum() {
        return cards.stream().mapToInt(Card::getNumber).sum();
    }

}
