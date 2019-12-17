package domain.user;

import domain.card.Card;
import exception.InvalidException;

import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {

    private static final double MINIMUM_BETTINGMONEY = 0d;

    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        if (name.isEmpty()) {
            throw new InvalidException(InvalidException.PLAYER_NAME_EMPTY_EXCEPTION);
        }
        if (bettingMoney <= MINIMUM_BETTINGMONEY) {
            throw new InvalidException(InvalidException.NEGATIVE_BETTING_EXCEPTION);
        }
        this.name = name.trim();
        this.bettingMoney = bettingMoney;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }
}
