package game;

import java.util.HashMap;
import java.util.Map;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

public class ProfitGenerator {
    private static final double DRAW_AMOUNT = 0.0;
    private static final double BLACKJACK_DIVIDEND_RATE = 1.5;
    private static final double LOSS_DIVIDEND_RATE = -1;


    private final Players players;
    private final Dealer dealer;

    public ProfitGenerator(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public Profits create() {
        if (dealer.isBlackjack()) {
            return createDealerIsBlackJack();
        }
        Map<String, Double> profits = makeBustPlayers();
        if (dealer.isBust()) {
            return createDealerIsBust(profits);
        }
        return createDealerIsNotMatch(profits);
    }

    private Profits createDealerIsBlackJack() {
        Map<String, Double> profits = new HashMap<>();
        for (Player player : players.findByBlackjack()) {
            profits.put(player.getName(), DRAW_AMOUNT);
        }
        for (Player player : players.findByNotBlackjack()) {
            profits.put(player.getName(), calculateLose(player));
        }
        return new Profits(profits);
    }

    private Map<String, Double> makeBustPlayers() {
        Map<String, Double> profits = new HashMap<>();
        for (Player player : players.findByBust()) {
            profits.put(player.getName(), calculateLose(player));
        }
        return profits;
    }

    private Profits createDealerIsBust(Map<String, Double> profits) {
        for (Player player : players.findByNotBust()) {
            profits.put(player.getName(), calculateProfit(player));
        }
        return new Profits(profits);
    }

    private Profits createDealerIsNotMatch(Map<String, Double> profits) {
        for (Player player : players.findByNotBust()) {
            profits.put(player.getName(), calculateAmount(player));
        }
        return new Profits(profits);
    }

    private double calculateAmount(Player player) {
        if (player.isBlackjack() || (player.getTotalScore() > dealer.getTotalScore())) {
            return calculateProfit(player);
        }
        return calculateLose(player);
    }

    private double calculateProfit(Player player) {
        if (player.isBlackjack()) {
            return player.getBettingMoney() * BLACKJACK_DIVIDEND_RATE;
        }
        return player.getBettingMoney();
    }

    private double calculateLose(Player player) {
        return player.getBettingMoney() * LOSS_DIVIDEND_RATE;
    }

}
