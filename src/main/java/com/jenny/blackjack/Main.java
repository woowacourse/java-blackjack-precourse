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
        play.showAllCardStatus(dealer, init.getPlayers());
        play.askAddCard(dealer, init.getPlayers());

        Result result = new Result();
        result.showResultValue(dealer, init.getPlayers());
        result.showResultMoney(dealer, init.getPlayers());
    }
}
