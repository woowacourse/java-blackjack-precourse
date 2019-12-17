/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.controller;

import domain.model.user.Dealer;
import domain.model.user.Player;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 게임 결과에따라 플레이어의 수익을 결정할 비율을 리턴하는 클래스입니다.
 * @since : 2019.12.17 화요일
 */
public class ProfitController {
    private static final double MINUS_ONE = -1;
    private static final double ZERO = 0;
    private static final double ONE = 1;
    private static final double BLACKJACK_RATE = 1.5;

    public static double getPlayerProfitForBlackJack(Player player) {

        if (player.isBlackJack()) {
            return ZERO;
        }

        return MINUS_ONE;
    }

    public static double getPlayerDefeatedProfitRate() {

        return MINUS_ONE;
    }

    public static double getPlayerWonProfitRate(Player player) {

        if (player.isBlackJack()) {
            return BLACKJACK_RATE;
        }

        return ONE;
    }

    public static double getPlayerResultForNormalFinishProcedure(Player player, Dealer dealer) {

        if (player.isBlackJack()) {
            return BLACKJACK_RATE;
        }

        if (player.isBurst()) {
            return MINUS_ONE;
        }

        return getPlayerResultForCompetingWithDealer(player, dealer);
    }

    public static double getPlayerResultForCompetingWithDealer(Player player, Dealer dealer) {

        if (player.getCurrentScore() < dealer.getCurrentScore()) {
            return MINUS_ONE;
        }

        if (player.getCurrentScore() > dealer.getCurrentScore()) {
            return ONE;
        }

        return ZERO; // 위에서 처리 되지 않을 경우는 플레이어와 딜러가 동률일 때 뿐이기에 0을 리턴
    }


}
