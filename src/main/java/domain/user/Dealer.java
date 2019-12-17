/*
 * @(#)Dealer.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain.user;

import common.BlackjackConfig;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    public Dealer() {
    }

    @Override
    public String toString() {
        return BlackjackConfig.DEALER_NAME;
    }

    public void win(double money) {
        profit += money;
    }

    public void lose(double money) {
        profit -= money;
    }
}
