package view.dto;

import domain.user.Gamers;
import domain.user.Player;

import java.util.List;

public class GameResult {
    private static final int EARN_RATE = -1;

    private final String name;
    private double money;

    public GameResult(Player player, Gamers gamers) {
        boolean dealerBlackJack = gamers.isDealerBlackJack();
        boolean isWinner = gamers.isWinner(player);

        this.name = player.getName();
        this.money = player.getResultMoney(dealerBlackJack, isWinner);
    }

    public GameResult(String name) {
        this.name = name;
    }

    public void calculateDealerMoney(List<GameResult> gameResults) {
        double moneySum = gameResults.stream()
                .mapToDouble(GameResult::getMoney)
                .sum();

        this.money = moneySum * EARN_RATE;
    }

    public double getMoney() {
        return money;
    }

    public String getResult() {
        return name + " : " + money;
    }
}
