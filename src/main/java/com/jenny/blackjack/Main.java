package com.jenny.blackjack;

import domain.user.Dealer;

public class Main {
    public static void main(String[] args) {

        InitialSetting init = new InitialSetting();
        init.askUserName();
        init.askBetMoney();

        Play play = new Play();
        Dealer dealer = new Dealer();
        play.distributeInitialCards(dealer, init.getPlayers());
        play.showCardStatus(dealer, init.getPlayers());
    }
}
