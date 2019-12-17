package woowacourse.blackjack;

import domain.user.Player;
import domain.user.Dealer;

import java.util.HashMap;
import java.util.List;

public class Calculator {
    private double dealerRevenue = 0;
    private HashMap<Player, Double> playersRevenue = new HashMap<Player, Double>();

    public void setWhenBlackJack(Dealer dealer, List<Player> players) {
        for (Player player: players) {
            this.setWhenBlackJack(player, dealer.isBlackJack());
        }
    }

    private void setWhenBlackJack(Player player, boolean isDealerBlackJack) {
        if (isDealerBlackJack) {
            this.setWhenDealerBlackJack(player);
            return;
        }
        this.setWhenNotDealerBlackJack(player);
    }

    private void setWhenDealerBlackJack(Player player) {
        double money = player.getBettingMoney();
        if (player.isBlackJack()) {
            this.setMoney(player, 0.0);
            return;
        }
        this.setMoney(player, -money);
    }

    private void setWhenNotDealerBlackJack(Player player) {
        double money = player.getBettingMoney();
        if (player.isBlackJack()) {
            this.setMoney(player, money * 2.5);
            return;
        }
        this.setMoney(player, 0.0);
    }

    public void setRevenue(Dealer dealer, List<Player> players) {
        if (dealer.isSumScoreBust()) {
            this.setWhenPlayerBust(players);
            return ;
        }
        this.setWhenNotDealerBust(dealer, players);
    }

    private void setWhenPlayerBust(List<Player> players) {
        for (Player player: players) {
            this.setWhenPlayerBustMoney(player);
        }
    }

    private void setWhenPlayerBustMoney(Player player) {
        double money = player.getBettingMoney();
        if (player.getCards().size() == 2 && player.isBlackJack()) {
            this.setMoney(player, money * 2.5);
            return;
        }
        if (!player.isSumScoreBust()) {
            this.setMoney(player, money);
            return;
        }
        this.setMoney(player, -money);
    }

    private void setWhenNotDealerBust(Dealer dealer, List<Player> players) {
        for (Player player: players) {
            this.setWhenNotDealerBustMoney(dealer, player);
        }
    }

    private void setWhenNotDealerBustMoney(Dealer dealer, Player player) {
        double money = player.getBettingMoney();
        if (player.getCards().size() == 2 && player.isBlackJack()) {
            this.setMoney(player, money * 2.5);
            return;
        }
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
