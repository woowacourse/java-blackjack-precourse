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
        if (player.isBlackJack()) return ZERO;
        return MINUS_ONE;
    }

    public static double getPlayerDefeatedProfitRate() {
        return MINUS_ONE;
    }

    public static double getPlayerWonProfitRate() {
        return ONE;
    }

    public static double getPlayerResultOnNormalFinishProcedure(Player player, Dealer dealer) {
        if (player.isBlackJack()) return BLACKJACK_RATE;

        if (player.isBurst()) return MINUS_ONE;

        if (player.getCurrentScore() == dealer.getCurrentScore()) return ZERO;

        if (player.getCurrentScore() < dealer.getCurrentScore()) return MINUS_ONE;

        if (player.getCurrentScore() > dealer.getCurrentScore()) return ONE;

        return 77777; // 위에서 처리 되지 않을 경우 : 그렇지만 모든 경우가 처리되기에 이 return문에 걸릴 일은 없음
    }

}
