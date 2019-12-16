package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Gamer{

    private static final Integer SIXTEEN = 16;

    public Dealer() {}

    // TODO 추가 기능 구현

    public boolean shouldHaveOneMoreCard(){
        return sumOfCard() <= SIXTEEN;
    }

    public double getProfit(List<Player> players){
        double dealerProfit = 0;
        for(int i =0; i<players.size(); i++){
            if(players.get(i).winGame(this) || players.get(i).drawGame(this)){
                dealerProfit -= players.get(i).getProfit();
            }else {
                dealerProfit -= players.get(i).getProfit();
            }
        }
        return dealerProfit;
    }

}
