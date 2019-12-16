package domain.user;

import common.BlackjackConfig;
import domain.card.Card;
import domain.card.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    @Override
    public String toString() {
        return name;
    }

    public double win() {
        profit += bettingMoney;
        return profit;
    }

    public double winWithBlackjack() {
        profit += bettingMoney * BlackjackConfig.BLACKJACK_RATE;
        return profit;
    }

    public double lose() {
        profit -= bettingMoney;
        return profit;
    }
}