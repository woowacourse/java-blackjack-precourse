package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Gamer{

    public Dealer() {}

    // TODO 추가 기능 구현

    public boolean shouldHaveOneMoreCard(){
        return sumOfCard() <= 16;
    }

    public double getProfit(List<Player> players, Dealer dealer){
        double dealerProfit = 0;
        for(int i =0; i<players.size(); i++){
            if(players.get(i).winGame(dealer) || players.get(i).drawGame(dealer)){
                dealerProfit -= players.get(i).getProfit();
            }else {
                dealerProfit -= players.get(i).getProfit();
            }
        }
        return dealerProfit;
    }

}
