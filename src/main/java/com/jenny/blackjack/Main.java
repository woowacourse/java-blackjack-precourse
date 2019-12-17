package com.jenny.blackjack;

import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        InitialSetting init = new InitialSetting();
        Play play = new Play();
        Result result = new Result();
        List<Player> players = new ArrayList<>();
        Dealer dealer = new Dealer();

        init.askUserName();
        init.askBetMoney(players);

        play.distributeInitialCards(dealer, players);
        play.showAllCardStatus(dealer, players);
        play.askAddCard(dealer, players);

        result.showResultValue(dealer, players);
        result.showResultMoney(dealer, players);
    }
}
