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
    public static int BUST_CONDITION = 21;

    private Dealer dealer;
    private List<Player> playerList;
    private HashMap<String, Double> playersMoney = new HashMap<>();
    private double dealerMoney = 0;

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
        for (Player player : playerList) {
            winDealerCase(player);
            winPlayerCase(player);
            winBiggerUserCase(player);
        }
    }

    private void winDealerCase(Player player) {
        if (player.isBust()) {
            winDealer(player);
        }
    }

    private void winPlayerCase(Player player) {
        if (dealer.isBust() && !player.isBust()) {
            winPlayer(player);
        }
    }

    private void winBiggerUserCase(Player player) {
        if (!dealer.isBust() && !player.isBust()) {
            winDealerIfBigger(player);
            winPlayerIfBigger(player);
            noWinnerIfSame(player);
        }
    }

    private void winDealerIfBigger(Player player) {
        if (player.getSumScore() < dealer.getSumScore()) {
            winDealer(player);
        }
    }

    private void winDealer(Player player) {
        playersMoney.put(player.getName(), -player.getBettingMoney());
        dealerMoney += player.getBettingMoney();
    }

    private void winPlayerIfBigger(Player player) {
        if (player.getSumScore() > dealer.getSumScore()) {
            winPlayer(player);
        }
    }

    private void winPlayer(Player player) {
        playersMoney.put(player.getName(), player.getBettingMoney());
        dealerMoney -= player.getBettingMoney();
    }

    private void noWinnerIfSame(Player player) {
        if (player.getSumScore() == dealer.getSumScore()) {
            draw(player);
        }
    }

    private void draw(Player player) {
        playersMoney.put(player.getName(), 0.0);
    }

    private void printResultMoney() {
        OutputView.printResultMoney(dealerMoney);
        for (Map.Entry<String, Double> e : playersMoney.entrySet()) {
            OutputView.printResultMoney(e.getKey(), e.getValue());
        }
    }
}
