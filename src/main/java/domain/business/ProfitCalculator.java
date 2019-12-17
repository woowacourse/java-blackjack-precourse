/*
 * @(#)ProfitCalculator.java        0.4 2019.12.17
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.business;

import domain.user.Dealer;
import domain.user.Player;

import java.util.HashMap;
import java.util.List;

/**
 * User들의 수익을 계산하는 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.4 2019.12.17
 */
public class ProfitCalculator {
    /**
     * Dealer와 관련된 정보를 출력할 때 Dealer의 이름으로 사용할 상수.
     */
    private static final String DEALER_NAME = "Dealer";

    /**
     * 모든 Player들의 배팅 머니를 합산하기 전에 해당 변수를 초기화 하기 위한 상수.
     */
    private static final double INIT_TOTAL_BETTING_MONEY = 0.;

    /**
     * Player가 Dealer에게 졌는지 확인하기 위한 상수.
     */
    private static final int PLAYER_LOSE = -1;

    /**
     * Player가 Dealer에게 이겼는지 확인하기 위한 상수.
     */
    private static final int PLAYER_WIN = 1;

    /**
     * 무승부일 때 수익을 계산하기 위한 상수.
     */
    private static final double DRAW_EARNING_RATE = 1.;

    /**
     * 승리일 때 수익을 계산하기 위한 상수.
     */
    private static final double WIN_EARNING_RATE = 2.;

    /**
     * 블랙잭(처음 2장의 care의 총합이 21)일 떄 수익을 계산하기 위한 상수.
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
     * 블랙잭 게임에 참여한 User들의 수익을 이름과 함께 저장할 HashMap.
     */
    private HashMap<String, Double> userProfits;

    /**
     * 블랙잭 게임에 참여한 User들의 수익을 계산하기 위한 ProfitCalculator 매개변수 생성자.
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
     * User들의 수익을 초기화하는 메소드.
     * Player들의 처음 배팅하는 금액은 모두 Dealer의 수익으로 들어간다고 설정하고 진행.
     * 이후 Player가 얻는 수익은 Dealer가 제공한다고 가정.
     */
    private void initUsersProfit() {
        userProfits = new HashMap<>();
        double totalBettingMoney = INIT_TOTAL_BETTING_MONEY;

        for (Player player : players) {
            double playerBettingMoney = player.getBettingMoney();

            userProfits.put(player.getName(), -playerBettingMoney);
            totalBettingMoney += playerBettingMoney;
        }
        userProfits.put(DEALER_NAME, totalBettingMoney);
    }

    /**
     * User들의 수익 계산을 진행하는 메소드.
     *
     * @return User들의 수익이 저장된 HashMap을 반환.
     */
    public HashMap<String, Double> calculateUsersFinalProfit() {
        for (Player player : players) {
            calculateSelectedPlayerProfit(player);
        }
        return userProfits;
    }

    /**
     * Player가 블랙잭인지 여부를 나눠서 수익 계산을 진행하는 메소드.
     *
     * @param player 수익을 계산할 Player.
     */
    private void calculateSelectedPlayerProfit(Player player) {
        if (player.isBlackJack()) {
            calculateSelectedPlayerIsBlackJack(player);
            return;
        }
        calculateSelectedPlayerIsNotBlackJack(player);
    }

    /**
     * Player가 블랙잭일 경우 Dealer가 블랙잭인지 여부로 나눠서 수익을 계산하는 메소드.
     *
     * @param player 블랙잭인지 확인할 Player.
     */
    private void calculateSelectedPlayerIsBlackJack(Player player) {
        if (dealer.isBlackJack()) {
            setSelectedPlayerProfitByEarningRate(player, DRAW_EARNING_RATE);
            return;
        }
        setSelectedPlayerProfitByEarningRate(player, BLACK_JACK_EARNING_RATE);
    }

    /**
     * Player가 블랙잭이 아닐 경우, 다양한 경기 결과들로 나눠서 수익을 계산하는 메소드.
     *
     * @param player 수익을 계산할 Player.
     */
    private void calculateSelectedPlayerIsNotBlackJack(Player player) {
        int playerResultOfGame = getGameResultOfSelectedPlayer(player);

        if (player.isBust() || dealer.isBlackJack() || (!dealer.isBust() && (playerResultOfGame == PLAYER_LOSE))) {
            return;
        }
        if (dealer.isBust() || (playerResultOfGame == PLAYER_WIN)) {
            setSelectedPlayerProfitByEarningRate(player, WIN_EARNING_RATE);
            return;
        }
        setSelectedPlayerProfitByEarningRate(player, DRAW_EARNING_RATE);
    }

    /**
     * Player와 Dealer의 총점을 비교하여 결과를 반환하는 메소드.
     *
     * @param player Dealer와 총점를 비교할 Player.
     * @return Player의 총점이 더 큰 경우 1, 무승부일 경우 0, 더 작을 경우 -1을 반환.
     */
    private int getGameResultOfSelectedPlayer(Player player) {
        return Integer.compare(player.getScore(), dealer.getScore());
    }

    /**
     * Player가 얻을 수익률에 따라 수익을 추가하고, 그 금액만큼 Dealer의 수익에서 삭감하는 메소드.
     *
     * @param player      수익을 계산할 Player.
     * @param earningRate Player가 얻을 수익률.
     */
    private void setSelectedPlayerProfitByEarningRate(Player player, double earningRate) {
        double playerProfit = player.getBettingMoney() * earningRate;

        userProfits.put(player.getName(), userProfits.get(player.getName()) + playerProfit);
        userProfits.put(DEALER_NAME, userProfits.get(DEALER_NAME) - playerProfit);
    }
}
