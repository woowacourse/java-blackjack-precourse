package domain.user;

import domain.card.Card;
import domain.card.Symbol;

import java.util.*;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {

    private static final double FIRST_TURN_BLACKJACK_WIN_BETTING_RATE = 1.5;

    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getName() {
        return this.name;
    }

    public double getBettingMoney() {
        return this.bettingMoney;
    }

    public int firstBlackJackWinnerMoney(int money) {
        return (int) (money * FIRST_TURN_BLACKJACK_WIN_BETTING_RATE);
    }
    // TODO 추가 기능 구현

}
