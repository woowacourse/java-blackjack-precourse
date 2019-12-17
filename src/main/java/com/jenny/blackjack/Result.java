package com.jenny.blackjack;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class Result {

    public void showResultValue(Dealer dealer, List<Player> players){
        Play play = new Play();
        play.showDealerCardStatus(dealer);
        System.out.println(calcResultValue(dealer.getCardsList()));
        for(Player p : players){
            play.showOneCardStatus(p);
            System.out.println(calcResultValue(p.getCardsList()));
        }
    }
}
