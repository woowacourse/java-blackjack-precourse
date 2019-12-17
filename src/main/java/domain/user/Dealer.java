package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {

    private static final String NAME = "딜러";
    private static final int ONEMORE_CARD_BASIS = 16;

    public Dealer() {
    }

    public boolean isUnberSixteen() {
        return calculateSum() <= ONEMORE_CARD_BASIS;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return Dealer.NAME;
    }

}
