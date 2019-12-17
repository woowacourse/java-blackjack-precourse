/*
 * @(#)PlayerFactory.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain.user;

public class PlayerFactory {
    public Player create(String name, double bettingMoney) {
        return new Player(name, bettingMoney);
    }
}
