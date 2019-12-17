package com.jenny.blackjack;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.List;

public class Result {

    public void showResultValue(Dealer dealer, List<Player> players){
        Play play = new Play();
        play.showDealerCardStatus(dealer);
        System.out.println(calcResultValue(dealer));
        for(Player p : players){
            play.showOneCardStatus(p);
            System.out.println(calcResultValue(p));
        }
    }

    public int calcResultValue(User user){
        List<Card> cards = user.getCardsList();
        int aceCnt = 0;
        int sum = user.getSumOfCards();

        for(Card c : cards){
            if(c.getSymbol().equals("ACE")){
                aceCnt++;
            }
        }
        for(int a = 0; a < aceCnt; a++){
            if(sum + 10 <= 21){
                sum += 10;
            }
        }
        return sum;
    }

    public boolean isBurst(User user){
        return user.getSumOfCards() > 21;
    }
}
