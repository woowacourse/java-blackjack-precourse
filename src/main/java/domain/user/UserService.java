/*
 * @(#)UserService.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain.user;

import common.BlackjackConfig;
import domain.BlackjackPrinter;
import domain.card.Deck;

//todo: check if can apply generic
public abstract class UserService {

    protected Deck deck;
    protected BlackjackPrinter blackjackPrinter;

    UserService(Deck deck, BlackjackPrinter blackjackPrinter) {
        this.deck = deck;
        this.blackjackPrinter = blackjackPrinter;
    }

    public void receiveDefaultCards(User user) {
        for (int i = 0; i < BlackjackConfig.DEFAULT_NUMBER; i++) {
            user.addCard(deck.pick());
        }
        blackjackPrinter.printUserState(user);
    }

    public abstract void confirmCards(User user);

    public void printResult(User user) {
        blackjackPrinter.printUserResult(user);
    }

    public void printProfit(User user) {
        blackjackPrinter.printProfit(user);
    }
}
