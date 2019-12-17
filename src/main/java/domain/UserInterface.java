/*
 * @(#)UserInterface.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain;

import domain.user.User;

public interface UserInterface {
    /* UserInterface는 사용자의 입력을 받는 역할을 합니다. 잘못된 입력을 받을 경우, 제대로 된 입력을 받을 때까지 반복합니다. */
    
    String[] extractNames();

    double getBettingMoney(String name);

    String getWillForMoreCard(User user);
}
