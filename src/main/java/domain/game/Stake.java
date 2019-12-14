package domain.game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            stake.put(key, -stake.get(key));
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

    @Override
    public String toString() {
        Set<String> players = new HashSet<>(stake.keySet());
        players.remove(Dealer.NAME);
        StringBuilder sb = new StringBuilder("\n## 최종수익\n");
        sb.append(Dealer.NAME + ": " + stake.get(Dealer.NAME) + "\n");
        for (String key : players) {
            sb.append(key + ": " + stake.get(key) + "\n");
        }
        return sb.toString();
    }
}
