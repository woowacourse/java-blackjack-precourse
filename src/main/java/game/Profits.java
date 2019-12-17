package game;

import java.util.Map;

public class Profits {
    private static final double REVERSAL_VALUE = -1;

    private final Map<String, Double> profits;

    public Profits(Map<String, Double> profits) {
        this.profits = profits;
    }

    public double getDealerProfit() {
        return profits.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum() * REVERSAL_VALUE;
    }

    public double getPlayerProfit(String name) {
        return profits.get(name);
    }

}
