/*
 * @(#)Blackjack.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain;

import java.util.List;

import api.UserApi;
import domain.user.*;

public class Blackjack {
    private UserApi userApi;

    private Dealer dealer;


    public Blackjack(UserApi userApi, Dealer dealer) {
        this.userApi = userApi;
        this.dealer = dealer;
    }


    public void play() {
        List<Player> players = userApi.join();

        userApi.receiveDefaultCards(dealer, players);

        userApi.confirmCards(players, dealer);

        userApi.match(dealer, players);

        userApi.analyze(dealer, players);
    }
}
