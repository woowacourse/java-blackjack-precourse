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
    static double dealerSum = 0;
    static final int BLACKJACK = 21;
    static int temp = 99999;
    View view = new View();
    static List<Double> playerMoneyList = new ArrayList<>();

    /**
     * 시작하자마자 블랙젝이있는경우 최종결과 리턴
     *
     * @param playerList 플레이어리스트
     * @param dealer     딜러객체
     */
    public void startBlackjack(List<Player> playerList, Dealer dealer) {
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

    /**
     * 최종 수익 리스트를 들어서 출력하는 기능
     *
     * @param playerList 플레이어 리스트
     * @param dealer     딜러객체
     */
    public void determineBlackjack(List<Player> playerList, Dealer dealer) {
        if (dealer.calculateSymbol() > BLACKJACK) {
            overDealerBlackjack(playerList);
            return;
        }
        makeResult(playerList);
    }

    /**
     * 딜러의 값이 21을 넘는경우
     *
     * @param playerList 플레이어 리스트
     */
    public void overDealerBlackjack(List<Player> playerList) {
        playerList.forEach(player -> playerMoneyList.add(player.getbettingMoney()));
        view.resultMsg(0.0, playerList, playerMoneyList);
    }

    /**
     * 결과를 만드는 과정
     *
     * @param playerList 플레이어 리스트
     */
    public void makeResult(List<Player> playerList) {
        for (Player player : playerList) {
            if (player.calculateSymbol() > BLACKJACK) {
                playerMoneyList.add(-player.getbettingMoney());
                dealerSum += player.getbettingMoney();
            }
            playerMoneyList.add(+player.getbettingMoney());
        }
        makeResultFilter(playerList);
        view.resultMsg(dealerSum, playerList, playerMoneyList);
    }

    /**
     * 21에 가장가까운 플레이어 뽑는 과정
     *
     * @param playerList 플레이어 리스트
     */
    public void makeResultFilter(List<Player> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerMoneyList.get(i) > 0) {
                makeResultFilterMore(playerList, i);
            }
        }
    }

    /**
     * 필터 과정중에 우승자 한명제외하고 나머지는 손해
     *
     * @param playerList 플레이어 리스트
     * @param index      인덱스
     */
    public void makeResultFilterMore(List<Player> playerList, int index) {
        if (temp > BLACKJACK - playerList.get(index).calculateSymbol()) {
            temp = BLACKJACK - playerList.get(index).calculateSymbol();
            return;
        }
        dealerSum += playerMoneyList.get(index);
        playerMoneyList.set(index, -playerMoneyList.get(index));
    }

    public void winner(List<Player> playerList,Dealer dealer){
        view.resultCardMsg(playerList,dealer);
        determineBlackjack(playerList,dealer);
    }
}
