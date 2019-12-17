package domain.game;

import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class BookKeeper {
    private double dealerBalance;
    private List<Double> playersBalance;

    public BookKeeper(Dealer dealer, List<Player> players) {
        double dealerBalance = 0;
        List<Double> playersBalance = new ArrayList<>();
        for (Player player : players) {
            double bettingMoney = player.getBettingMoney();
            playersBalance.add(new Double(-bettingMoney));
            dealerBalance += bettingMoney;
        }
        this.dealerBalance = dealerBalance;
        this.playersBalance = playersBalance;
    }

    public void updateBalance(Dealer dealer) {

    }

    public double getDealerBalance() {
        return this.dealerBalance;
    }

    public List<Double> getPlayersBalance() {
        return this.playersBalance;
    }
}
