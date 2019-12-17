package game;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class ProfitDecider {

    private static final double BLACK_JACK_MULTIPLE = 1.5d;
    private static final double NEGATIVE_POSITIVE_BASIS = -1d;
    private static final double NO_PROFIT = 0d;

    public static double calculatePlayerProfit(Dealer dealer, Player player) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return player.getBettingMoney() * BLACK_JACK_MULTIPLE;
        }
        if (player.isBursted()) {
            return -player.getBettingMoney();
        }
        if (dealer.isBursted()) {
            return player.getBettingMoney();
        }
        return compareDealerPlayer(dealer, player);
    }

    private static double compareDealerPlayer(Dealer dealer, Player player) {
        if (player.calculateSum() < dealer.calculateSum()) {
            return -player.getBettingMoney();
        }
        if (player.calculateSum() > dealer.calculateSum()) {
            return player.getBettingMoney();
        }
        return NO_PROFIT;
    }

    public static double calculateDealerProfit(Dealer dealer, List<Player> players) {
        return players.stream().mapToDouble(player ->
                calculatePlayerProfit(dealer, player)).map(ProfitDecider::changeNegativePositive).sum();
    }

    private static double changeNegativePositive(double money) {
        return money * NEGATIVE_POSITIVE_BASIS;
    }
}
