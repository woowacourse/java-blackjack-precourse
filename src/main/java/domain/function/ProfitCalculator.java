package domain.function;

import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class ProfitCalculator {

    public static List<Integer> getPlayerProfitList(Dealer dealer, List<Player> playerList) {
        List<Integer> playerProfitList = new ArrayList<>();
        for (Player player : playerList) {
            OutcomeRateCalculator calculator = new OutcomeRateCalculator(dealer.createBlackjackUserResult(), player.createBlackjackUserResult());
            double profitRate = calculator.getPlayerOutcomeRate();
            int profit = player.getProfit(profitRate);
            playerProfitList.add(profit);
        }
        return playerProfitList;
    }

    public static int getDealerProfitSum(List<Integer> playerProfitList) {
        int dealerProfitSum = 0;
        for (int playerProfit : playerProfitList) {
            dealerProfitSum += playerProfit;
        }
        return dealerProfitSum * -1;
    }

}
