/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * InputView.java
 * 출력을 담당하는 클래스
 *
 * @author      Sorin Jin
 * @version     1.0
 * @date        16 Dec 2019
 *
 */

package view;

import domain.user.User;
import util.Message;

public class OutputView {
    public void whatIsPlayerName() {
        System.out.println(Message.INPUT_PLAYER_NAME.getMessage());
    }

    public void howMuchBettingMoney(String name) {
        System.out.println(name + Message.INPUT_BETTING_MONEY.getMessage());
    }

    public void printCards(User player) {
        System.out.println(player.toStringCards());
    }
}
