package game;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class BattingMoneyDecider {

    public static double getPlayerMoney(Dealer dealer, Player player) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return player.getBettingMoney() * 1.5;
        }

        if (player.isBursted()) {
            return -player.getBettingMoney();
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

    public static double getDealerMoney(Dealer dealer, List<Player> players) {
        return players.stream().mapToDouble(player -> getDealerMoney(dealer, player)).sum();
    }

    private static double getDealerMoney(Dealer dealer, Player player) {
        if (player.isBursted()) {
            return player.getBettingMoney();
        }

        if (dealer.calculateSum() > player.calculateSum()) {
            return player.getBettingMoney();
        }

        return 0;
    }
}
