package domain.controller;


import domain.model.user.Dealer;
import domain.model.user.Player;

import java.util.ArrayList;

// 금액관련 계산만해서 돌려보내기.
public class ProfitController {
    private static final double MINUS_ONE = -1;
    private static final double ZERO = 0;
    private static final double ONE = 1;
    private static final double BLACKJACK_RATE = 1.5;
    private double totalPlayerProfit = 0;

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

        return 7777; // test
    }

}
