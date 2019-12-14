package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Gamer {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String toString() {
        return name + " : " + cardsToString(getCards());
    }
}
