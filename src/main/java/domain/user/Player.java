/*
 * @(#)Player.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain.user;

import common.BlackjackConfig;

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

    @Override
    public String toString() {
        return name;
    }
}