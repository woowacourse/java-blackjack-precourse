package system;

import domain.user.Dealer;
import domain.user.Player;
import view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static view.OutputView.printResultStatus;

public class ResultSystem {
    private static int BUST_CONDITION = 21;

    private Dealer dealer;
    private List<Player> playerList;
    private HashMap<String, Integer> playersMoney = new HashMap<>();
    private int dealerMoney = 0;

    public ResultSystem(Dealer dealer, List<Player> playerList) {
        this.dealer = dealer;
        this.playerList = new ArrayList<>(playerList);
    }

    public void getResult() {
        printUserStatus();
        setResultMoney();
        printResultMoney();
    }

    private void printUserStatus() {
        printResultStatus(dealer);
        for (Player player : playerList) {
            printResultStatus(player);
        }
    }

    private void setResultMoney() {
        if (isDealerBust()) {
            rewardAllPlayer();
            return;
        }
        for (Player player : playerList) {
            rewardDealerIfWin(player);
            rewardPlayerIfWin(player);
            rewardNothingIfDraw(player);
        }
    }

    private boolean isDealerBust() {
        return dealer.getSumScore() > BUST_CONDITION;
    }

    private void rewardAllPlayer() {
        for (Player player : playerList) {
            playersMoney.put(player.getName(), (int) player.getBettingMoney());
        }
    }

    private void rewardDealerIfWin(Player player) {
        if (player.getSumScore() < dealer.getSumScore()) {
            playersMoney.put(player.getName(), (int) -player.getBettingMoney());
            dealerMoney += player.getBettingMoney();
        }
    }

    private void rewardPlayerIfWin(Player player) {
        if (player.getSumScore() > dealer.getSumScore()) {
            playersMoney.put(player.getName(), (int) player.getBettingMoney());
            dealerMoney -= player.getBettingMoney();
        }
    }

    private void rewardNothingIfDraw(Player player) {
        if (player.getSumScore() == dealer.getSumScore()) {
            playersMoney.put(player.getName(), 0);
        }
    }

    private void printResultMoney() {
        OutputView.printResultMoney(dealerMoney);
        for (Map.Entry<String, Integer> e : playersMoney.entrySet()) {
            OutputView.printResultMoney(e.getKey(), e.getValue());
        }
    }
}
