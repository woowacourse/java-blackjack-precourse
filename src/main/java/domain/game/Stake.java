package domain.game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.user.Contender;
import domain.user.Contenders;
import domain.user.Dealer;

public class Stake {
    private HashMap<String, Double> stake = new HashMap<>();

    public Stake(Contenders contenders) {
        for (Contender contender : contenders) {
            stake.put(contender.getName(), contender.getBettingMoney());
        }
        initialize();
    }

    private void initialize() {
        double total = getTotal();
        for (String name : stake.keySet()) {
            stake.put(name, -stake.get(name));
        }
        stake.put(Dealer.NAME, total);
    }

    private double getTotal() {
        double total = 0;
        for (double bettingMoney : stake.values()) {
            total += bettingMoney;
        }
        return total;
    }

    public void calculate(List<Contender> winners) {
        for (Contender winner : winners) {
            stake.put(winner.getName(), winner.getBettingMoney());
            stake.put(Dealer.NAME, stake.get(Dealer.NAME) - winner.getBettingMoney());
        }
    }

    @Override
    public String toString() {
        Set<String> names = new HashSet<>(stake.keySet());
        names.remove(Dealer.NAME);
        StringBuilder sb = new StringBuilder("\n## 최종수익\n");
        sb.append(Dealer.NAME + ": ").append(stake.get(Dealer.NAME)).append("\n");
        for (String name : names) {
            sb.append(name).append(": ").append(stake.get(name)).append("\n");
        }
        return sb.toString();
    }
}
