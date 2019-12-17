package utils;

import domain.user.Dealer;
import domain.user.Gamers;
import domain.user.Player;

public class EarningCalculator {
    private static final double BLACKJACK_RATE = 1.5;
    private static final double NON_BLACKJACK_RATE = 1;

    public static double calculateEarning(Player player, Dealer dealer) {
        if (player.isBust() || dealer.isBust()) {
            return calculateBustEarning(player, dealer);
        }
        if (player.calculateFinalScore() == dealer.calculateFinalScore()) {
            return 0;
        }
        if (player.calculateFinalScore() < dealer.calculateFinalScore()) {
            return -player.getBettingMoney();
        }
        return player.getBettingMoney() * calculateBlackJack(player);
    }

    private static double calculateBustEarning(Player player, Dealer dealer) {
        if (player.isBust()) {
            return -player.getBettingMoney();
        }

        return player.getBettingMoney() * calculateBlackJack(player);
    }

    private static double calculateBlackJack(Player player) {
        if (player.isBlackJack()) {
            return BLACKJACK_RATE;
        }

        return NON_BLACKJACK_RATE;
    }

    public static double calculateTotalEarning(Gamers gamers) {
        double totalEarning = 0;

        for (Player player : gamers.getPlayers()) {
            totalEarning += EarningCalculator.calculateEarning(player, gamers.getDealer());
        }

        return totalEarning;
    }
}
