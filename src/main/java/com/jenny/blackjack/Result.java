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

    /*
     * 카드 점수의 합을 계산하는 메서드
     * 에이스의 경우, 1또는 11로 사용 가능하므로
     * 상황에따라 10을 더하는것이 유리하면 그렇게 한다
     * 블랙잭 조건이 되는 경우는 21이 아닌 22로 계산하여
     * 금액 계산시 21과 구분짓도록 한다
     */
    public int calcResultValue(User user){
        List<Card> cards = user.getCardsList();
        int aceCnt = getAceCnt(cards);
        int sum = user.getSumOfCards();

        for(int a = 0; a < aceCnt; a++){
            if(sum + 10 <= 21){
                sum += 10;
            }else{
                break;
            }
        }
        if(sum == 21 && cards.size() == 2){
            sum++;
        }
        return sum;
    }

    public int getAceCnt(List<Card> cards){
        int cnt = 0;
        for(Card c : cards){
            if(c.getSymbol().equals("ACE")){
                cnt++;
            }
        }
        return cnt;
    }

    public boolean isBurst(User user){
        return user.getSumOfCards() > 21;
    }

    public boolean isBlackJack(User user){
        return calcResultValue(user) == 22;
    }
}
