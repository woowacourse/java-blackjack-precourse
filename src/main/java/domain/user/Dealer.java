package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Person {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public String getName() {
        return "딜러";
    }

    public String toString() {
        return "name : 딜러" + ", bettingMoney : ?";
    }
}
