/*
 * @(#)UserInterface.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain;

import domain.user.User;

public interface UserInterface {
    String[] extractNames();

    double getBettingMoney(String name);

    String getWillForMoreCard(User user);
}
