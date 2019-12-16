package woowacourse.blackjack;

import domain.user.Player;
import domain.user.Dealer;

import java.util.HashMap;
import java.util.List;

public class Calculator {
    private double dealerRevenue = 0;
    private HashMap<Player, Double> playersRevenue = new HashMap<Player, Double>();

    public void setRevenue(Dealer dealer, List<Player> players) {
        if (dealer.isSumScoreBust()) {
            this.isDealerBust(players);
            return ;
        }
        this.isNotDealerBust(dealer, players);
    }

    private void isDealerBust(List<Player> players) {
        for (Player player: players) {
            this.whenDealerBust(player);
        }
    }

    private void whenDealerBust(Player player) {
        double money = player.getBettingMoney();
        if (!player.isSumScoreBust()) {
            this.setMoney(player, money);
            return;
        }
        this.setMoney(player, -money);
    }

    private void isNotDealerBust(Dealer dealer, List<Player> players) {
        for (Player player: players) {
            this.getNotDealerBustMoney(dealer, player);
        }
    }

    private void getNotDealerBustMoney(Dealer dealer, Player player) {
        double money = player.getBettingMoney();
        if (dealer.getSumScore() < player.getSumScore() && !player.isSumScoreBust()) {
            this.setMoney(player, money);
            return;
        }
        if (dealer.getSumScore() > player.getSumScore() || player.isSumScoreBust()) {
            this.setMoney(player, -money);
            return;
        }
        this.setMoney(player, 0.0);
    }

    private void setMoney(Player player, double money) {
        this.dealerRevenue -= money;
        playersRevenue.put(player, money);
    }

    public double getDealerRevenue() {
        return this.dealerRevenue;
    }

    public double getPlayerRevenue(Player player) {
        return playersRevenue.get(player);
    }
}
