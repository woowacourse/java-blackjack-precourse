package domain.game;

import java.util.HashMap;
import java.util.List;

import domain.user.Contender;
import domain.user.Dealer;

public class Stake {
    private static final double ZERO = 0;
    private HashMap<String, Double> stake;

    public Stake(List<Contender> contenders) {
        this.stake = new HashMap<>();
        for (Contender contender : contenders) {
            stake.put(contender.getName(), contender.getBettingMoney());
        }
        initialize();
    }

    private void initialize() {
        double total = getTotal();
        for (String key : stake.keySet()) {
            stake.put(key, ZERO);
        }
        stake.put(Dealer.NAME, total);
    }

    private double getTotal() {
        double total = ZERO;
        for (double bettingMoney : stake.values()) {
            total += bettingMoney;
        }
        return total;
    }
}
