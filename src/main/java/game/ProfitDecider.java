package game;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class BettingMoneyDecider {

    private static final double BLACK_JACK_MULTIPLE = 1.5d;
    private static final double NEGATIVE_POSITIVE_BASIS = -1d;

    public static double calculatePlayerProfit(Dealer dealer, Player player) {
        if (player.isBursted()) {
            return -player.getBettingMoney();
        }

        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return player.getBettingMoney() * BLACK_JACK_MULTIPLE;
        }

        if (dealer.isBursted()) {
            return player.getBettingMoney();
        }

        if (player.calculateSum() < dealer.calculateSum()) {
            return -player.getBettingMoney();
        }

        if (player.calculateSum() > dealer.calculateSum()) {
            return player.getBettingMoney();
        }

        return 0;
    }

    public static double calculateDealerProfit(Dealer dealer, List<Player> players) {
        return players.stream().mapToDouble(player -> calculatePlayerProfit(dealer, player)).map(BettingMoneyDecider::changeNegativePositive).sum();
    }

    private static double changeNegativePositive(double money) {
        return money * NEGATIVE_POSITIVE_BASIS;
    }
}
