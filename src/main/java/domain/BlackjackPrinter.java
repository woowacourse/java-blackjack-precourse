/*
 * @(#)BlackjackPrinter.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain;

import java.util.List;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public interface BlackjackPrinter {

    void printUserState(User user);

    void printUserResult(User user);

    void printDealerHit(User user);

    void printRequestForNames();

    void printRequestForMoney(String name);

    void printRequestForHit(User user);

    void printStart(Dealer dealer, List<Player> players);

    void printBreaktime();

    void printError(RuntimeException e);

    void printProfit(User user);

    void printBurst(User user);

    void printProfitGuide();
}
