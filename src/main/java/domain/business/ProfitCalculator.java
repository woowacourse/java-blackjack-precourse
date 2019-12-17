/*
 * @(#)ProfitCalculator.java        0.1 2019.12.17
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.business;

import domain.user.Dealer;
import domain.user.Player;

import java.util.HashMap;
import java.util.List;

/**
 * User의 수익을 계산하는 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.1 2019.12.17
 */
public class ProfitCalculator {
    /**
     * Dealer와 관련된 정보를 출력할 때 이름으로 사용할 상수.
     */
    private static final String DEALER_NAME = "Dealer";

    private static final int PLAYER_LOSE = -1;

    private static final int PLAYER_WIN = 1;

    /**
     * 무승부일때 수익을 계산하기 위한 상수.
     */
    private static final double DRAW_EARNING_RATE = 1.;

    /**
     * 승리시 수익을 계산하기 위한 상수.
     */
    private static final double WIN_EARNING_RATE = 2.;

    /**
     * 블랙잭의 수익을 계산하기 위한 상수.
     */
    private static final double BLACK_JACK_EARNING_RATE = 2.5;

    /**
     * 블랙잭 게임에 참여한 Dealer.
     */
    private Dealer dealer;

    /**
     * 블랙잭 게임에 참여한 Player들.
     */
    private List<Player> players;

    /**
     * 블랙잭 게임에 참여한 User들의 수익을 저장할 usersProfit HashMap.
     */
    private HashMap<String, Double> usersProfit;

    /**
     * 블랙잭 게임에 참여한 User들의 수익을 계산하기 위해 기본 설정을 진행하는 매개변수 생성자.
     *
     * @param dealer  블랙잭 게임에 참여한 Dealer.
     * @param players 블랙잭 게임에 참여한 Player들.
     */
    public ProfitCalculator(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
        initUsersProfit();
    }

    /**
     * 처음 User들의 수익을 초기화하는 메소드.
     */
    private void initUsersProfit() {
        usersProfit = new HashMap<>();
        double totalBettingMoney = 0.;

        for (Player player : players) {
            double playerBettingMoney = player.getBettingMoney();
            usersProfit.put(player.getName(), -playerBettingMoney);
            totalBettingMoney += playerBettingMoney;
        }
        usersProfit.put(DEALER_NAME, totalBettingMoney);
    }

    /**
     * User들의 수익의 계산을 진행하는 메소드.
     *
     * @return User들의 수익이 저장된 HashMap을 반환.
     */
    public HashMap<String, Double> calculateUsersFinalProfit() {
        for (Player player : players) {
            calculatePlayerProfit(player);
        }
        return usersProfit;
    }

    /**
     * Player의 경기 결과에 따라 수익 계산을 진행하는 메소드.
     *
     * @param player 수익을 계산할 Player.
     */
    private void calculatePlayerProfit(Player player) {
        if (player.isBlackJack()) {
            calculatePlayerBlackJack(player);
            return;
        }
        calculatePlayerNotBlackJack(player);
    }

    /**
     * Player가 블랙잭일 경우 Dealer가 블랙잭인지 여부로 나눠서 수익을 계산하는 메소드.
     *
     * @param player 블랙잭인지 확인할 Player.
     */
    private void calculatePlayerBlackJack(Player player) {
        if (dealer.isBlackJack()) {
            setPlayerProfitByEarningRate(player, DRAW_EARNING_RATE);
            return;
        }
        setPlayerProfitByEarningRate(player, BLACK_JACK_EARNING_RATE);
    }

    /**
     * Player가 블랙잭이 아닐 경우, 다양한 경기 결과들로 나눠서 수익을 계산하는 메소드.
     *
     * @param player 수익을 계산할 Player.
     */
    private void calculatePlayerNotBlackJack(Player player) {
        int playerResultOfGame = getResultOfGame(player);
        boolean isDealerBust = dealer.isBust();
        if (player.isBust() || dealer.isBlackJack() || (!isDealerBust && (playerResultOfGame == PLAYER_LOSE))) {
            return;
        }
        if (isDealerBust || (playerResultOfGame == PLAYER_WIN)) {
            setPlayerProfitByEarningRate(player, WIN_EARNING_RATE);
            return;
        }
        setPlayerProfitByEarningRate(player, DRAW_EARNING_RATE);
    }

    /**
     * Player와 Dealer의 게임 결과를 반환하는 메소드.
     *
     * @param player Dealer와 점수를 비교할 Player.
     * @return Player의 점수가 더 큰 경우 1, 무승부일 경우 0, 더 작을 경우 -1을 반환.
     */
    private int getResultOfGame(Player player) {
        return Integer.compare(player.getScore(), dealer.getScore());
    }

    /**
     * Player가 얻을 수익의 비율에 따라 수익을 추가하고, 그 금액만큼 Dealer의 수익에서 삭감하는 메소드.
     *
     * @param player      수익을 계산할 Player.
     * @param earningRate Player가 얻을 수익률.
     */
    private void setPlayerProfitByEarningRate(Player player, double earningRate) {
        double playerProfit = player.getBettingMoney() * earningRate;
        usersProfit.put(player.getName(), usersProfit.get(player.getName()) + playerProfit);
        usersProfit.put(DEALER_NAME, usersProfit.get(DEALER_NAME) - playerProfit);
    }
}
