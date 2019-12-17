/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.model.user;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 게임 참여자를 의미하는 객체입니다.
 * @since : 2019.12.17 화요일
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

    public double getProfitByResult(double profitRate) {
        return this.bettingMoney * profitRate;
    }

}
