package dto;

import domain.user.Player;
import domain.user.Players;

import java.util.List;

public class Result {

    private static final int DEALER_EARN_RATE = -1;

    private final String name;
    private double rewardMoney;

    public Result( Players players, Player player ) {
        boolean dealerBlackJack = players.isDealerBlackJack();
        boolean isWin = players.isWin(player);

        this.name = player.getName();
        this.rewardMoney = player.getRewardMoney(dealerBlackJack, isWin);
    }

    public Result( Player player ) {
        this.name = player.getName();
    }

    public void calculateDealerRewardMoney( List<Result> results ) {
        double playersMoneySum = results.stream()
                .mapToDouble(Result::getRewardMoney)
                .sum();
        rewardMoney = playersMoneySum * DEALER_EARN_RATE;
    }

    private double getRewardMoney() {
        return rewardMoney;
    }

    public String getResult() {
        return name + ": " + rewardMoney;
    }
}
