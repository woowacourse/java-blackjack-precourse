/*
 * @(#)Blackjack.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

import java.util.List;

import api.BlackjackApi;
import domain.user.*;

class Blackjack {
    private BlackjackApi blackjackApi;

    private Dealer dealer;

    Blackjack(BlackjackApi blackjackApi, Dealer dealer) {
        this.blackjackApi = blackjackApi;
        this.dealer = dealer;
    }

    void play() {
        List<Player> players = blackjackApi.join();
        blackjackApi.shuffle();
        blackjackApi.receiveDefaultCards(dealer, players);
        blackjackApi.confirmCards(players, dealer);
        blackjackApi.match(dealer, players);
        blackjackApi.analyze(dealer, players);
    }
}
