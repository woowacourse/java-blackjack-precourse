/*
 * class: SelectionWinner
 *
 * version: 1.0v
 *
 * date: 2019.12.17
 *
 * 이 프로그램의 저작권은 정은석에게 있습니다.
 * Copyright 2019.12.17
 */

package domain.winner;

import domain.output.View;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * 최종결과를 보여주는 클레스
 */
public class SelectionWinner {
    static final int BLACKJACK = 21;
    View view = new View();

    /**
     * 시작하자마자 블랙젝이있는경우 최종결과 리턴
     *
     * @param playerList 플레이어리스트
     * @param dealer     딜러객체
     */
    public void startBlackjack(List<Player> playerList, Dealer dealer) {
        double dealerSum = 0;
        List<Double> playerMoneyList = new ArrayList<>();

        if (dealer.calculateSymbol() == BLACKJACK) {
            getDealerBlackjack(playerList, playerMoneyList, dealerSum);
            return;
        }
        getPlayerBlackjack(playerList, playerMoneyList, dealerSum);
    }

    /**
     * 딜러와 같이 플레이어가 블렉젝인경우
     *
     * @param playerList      플레이어 리스트
     * @param playerMoneyList 플레이어의 최종수익 리스트
     * @param dealerSum       딜러의 수익
     */
    public void getDealerBlackjack(List<Player> playerList, List<Double> playerMoneyList, Double dealerSum) {
        for (Player player : playerList) {
            if (player.calculateSymbol() != BLACKJACK) {
                playerMoneyList.add(-player.getbettingMoney());
                dealerSum += player.getbettingMoney();
                continue;
            }
            playerMoneyList.add(0.0);
        }
        view.resultMsg(dealerSum, playerList, playerMoneyList);
    }

    /**
     * 플레이어만 블랙잭인 경우
     *
     * @param playerList      플레이어 리스트
     * @param playerMoneyList 플레이어의 최종수익 리스트
     * @param dealerSum       딜러의 수익
     */
    public void getPlayerBlackjack(List<Player> playerList, List<Double> playerMoneyList, Double dealerSum) {
        for (Player player : playerList) {
            if (player.calculateSymbol() != BLACKJACK) {
                playerMoneyList.add(-player.getbettingMoney());
                dealerSum += player.getbettingMoney();
                continue;
            }
            playerMoneyList.add((1.5) * player.getbettingMoney());
            dealerSum -= (0.5) * player.getbettingMoney();
        }
        view.resultMsg(dealerSum, playerList, playerMoneyList);
    }
}
