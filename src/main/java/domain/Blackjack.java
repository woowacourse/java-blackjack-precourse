/*
 * @(#)Blackjack.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain;

import java.util.List;

import api.BlackjackApi;
import domain.user.*;

public class Blackjack {
    private BlackjackApi blackjackApi;

    private Dealer dealer;


    public Blackjack(BlackjackApi blackjackApi, Dealer dealer) {
        this.blackjackApi = blackjackApi;
        this.dealer = dealer;
    }


    public void play() {
        List<Player> players = blackjackApi.join();

        blackjackApi.receiveDefaultCards(dealer, players);

        blackjackApi.confirmCards(players, dealer);

        blackjackApi.match(dealer, players);

        blackjackApi.analyze(dealer, players);
    }
}
