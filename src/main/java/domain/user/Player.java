package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Player
 *
 * @author hyochan
 * @version 0.0.1
 * @since 2019-12-14
 */

public class Player extends Dealer {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }
}
