/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.model.user;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends BlackJackParticipant {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getName() {
        return this.name;
    }

    public double getProfitByBettingMoney(double profitRate) {
        return this.bettingMoney * profitRate;
    }

}
