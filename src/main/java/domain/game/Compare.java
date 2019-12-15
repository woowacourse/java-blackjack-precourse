/*
 * Compare
 *
 * ver 1.0
 *
 * 2019.12.15
 *
 * CopyRight
 *
 */
package domain.game;

import domain.user.Dealer;
import domain.user.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Compare {
    /*
     * Compare 클래스 입니다.
     * Dealer 와 Person 객체가 가지고 있는 점수를 비교하며
     * 최종결과를 출력하는 클래스입니다.
     */
    private final Map<String, Integer> scoreTable = new HashMap<>();
    private double dealerMoney = 0.0;

    public void calculatePlayers(ArrayList<Player> playerList, Dealer dealer) {
        for (Player player : playerList) {
            verify(player, dealer);
        }
    }

    public void printAllBetting() {
        System.out.print( "\n\n");
        System.out.println( "## 최종수익");
        System.out.println( "딜러: " + (int)dealerMoney);
        for (Map.Entry<String, Integer> entry : scoreTable.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void printAllResult(ArrayList<Player> playerList, Dealer dealer) {
        printFinalDealer(dealer);
        for (Player player : playerList) {
            printFinalPlayer(player);
        }
    }

    public void verify(Player player, Dealer dealer) {
        if (player.getScore() > 21) {
            looseMoney(player);
        } else if (dealer.getScore() > 21) {
            getMoney(player);
        } else if (player.getScore() > dealer.getScore()) {
            getMoney(player);
        } else if (player.getScore() < dealer.getScore()) {
            looseMoney(player);
        } else if (player.getScore() == dealer.getScore()) {
            winWin(player);
        }
    }

    public void winWin(Player player) {
        scoreTable.put(player.getName(), 0);
    }

    public void getMoney(Player player) {
        double playerMoney = 0.0;
        if (player.getDeck().size() == 2 && player.getScore() == 21) {
            blackJack(player);
        } else {
            playerMoney += player.getBettingMoney();
            dealerMoney -= playerMoney;
            scoreTable.put(player.getName(), (int)playerMoney);
        }
    }

    public void looseMoney(Player player) {
        double playerMoney = 0.0;
        playerMoney -= player.getBettingMoney();
        dealerMoney -= playerMoney;
        scoreTable.put(player.getName(), (int)playerMoney);
    }

    public void blackJack(Player player) {
        double playerMoney = player.getBettingMoney();
        playerMoney *= 1.5;
        dealerMoney -= playerMoney;
        scoreTable.put(player.getName(), (int)playerMoney);
    }

    public void printFinalDealer(Dealer dealer) {
        String deckString = String.join(",", dealer.getDeck());
        System.out.println("딜러 카드: " + deckString + " -결과:" + dealer.getScore());
    }


    public void printFinalPlayer(Player player) {
        String deckString = String.join(",", player.getDeck());
        System.out.println(player.getName() + "카드: " + deckString + " -결과:" + player.getScore());
    }

}
