package domain.game;

import domain.user.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookKeeper {
    private static final double REWARD_FACTOR = 1.5;
    private static final double NO_REWARD_FACTOR = 1.0;

    private double dealerCashFlow;
    private HashMap<String, Double> playerCashFlows;

    public BookKeeper(Players players) {
        double dealerCashFlow = 0;
        HashMap<String, Double> playerCashFlows = players.initializeCashFlows();
        for (Double cashFlow : playerCashFlows.values()) {
            dealerCashFlow -= cashFlow;
        }
        this.dealerCashFlow = dealerCashFlow;
        this.playerCashFlows = playerCashFlows;
    }

    public void updateCashFlows(List<Player> winners, boolean blackJack) {
        for (Player player : winners) {
            double cashFlow = applyRewardFactor(blackJack) * player.getBettingMoney();
            this.playerCashFlows.replace(player.getName(), cashFlow);
            this.dealerCashFlow -= cashFlow;
        }
    }

    private double applyRewardFactor(boolean blackJack) {
        if (blackJack) {
            return REWARD_FACTOR;
        }
        return NO_REWARD_FACTOR;
    }

    public String getDealerCashFlowInfo() {
        return "딜러: " + this.dealerCashFlow;
    }

    public List<String> getPlayerCashFlowInfo() {
        List<String> cashFlowInfo = new ArrayList<>();
        for (String playerName : this.playerCashFlows.keySet()) {
            cashFlowInfo.add(getPlayerCashFlowInfo(playerName));
        }
        return cashFlowInfo;
    }

    public String getPlayerCashFlowInfo(String playerName) {
        return playerName + ": " + this.playerCashFlows.get(playerName);
    }

    public double getDealerCashFlow() {
        return this.dealerCashFlow;
    }

    public HashMap<String, Double> getPlayerCashFlows() {
        return this.playerCashFlows;
    }
}
