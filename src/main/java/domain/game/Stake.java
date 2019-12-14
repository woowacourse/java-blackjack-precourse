package domain.game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.user.Contender;
import domain.user.Dealer;

public class Stake {
    private HashMap<String, Double> stake = new HashMap<>();

    public Stake(List<Contender> contenders) {
        for (Contender contender : contenders) {
            stake.put(contender.getName(), contender.getBettingMoney());
        }
        initialize();
    }

    private void initialize() {
        double total = getTotal();
        for (String key : stake.keySet()) {
            stake.put(key, -stake.get(key));
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
        Set<String> players = new HashSet<>(stake.keySet());
        players.remove(Dealer.NAME);
        StringBuilder sb = new StringBuilder("\n## 최종수익\n");
        sb.append(Dealer.NAME + ": ").append(stake.get(Dealer.NAME)).append("\n");
        for (String key : players) {
            sb.append(key).append(": ").append(stake.get(key)).append("\n");
        }
        return sb.toString();
    }
}
