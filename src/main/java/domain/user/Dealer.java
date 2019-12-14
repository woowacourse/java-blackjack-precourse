package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Gamer {

    public Dealer() {
    }

    public String toString() {
        return "딜러 : " + cardsToString(getCards());
    }
}
