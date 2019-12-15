/*
 * Betting
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
import java.util.List;
import java.util.Scanner;

public class Betting {
    /*
     * Betting 클래스 입니다.
     * 게임의 실질적인 운영을 담당하는 클래스 입니다.
     */
    private final Dealer dealer = new Dealer();
    private final Try trying = new Try();
    private final ArrayList<Player> playerList = Set.gamePlayer();

    public void game() {
        trying.shuffleCard();
        firstTime();
        allPlayerKeepGoing();
        trying.keepDealerGoing(dealer);
        Compare compare = new Compare();
        compare.printAllResult(playerList, dealer);
        compare.calculatePlayers(playerList, dealer);
        compare.printAllBetting();
    }


    public void firstTime() {
        List<String> names = new ArrayList<String>();
        trying.firstTimeDealer(dealer);
        for (Player player : playerList) {
            names.add(player.getName());
            trying.firstTimePlayer(player);
        }
        printFirst(String.join(",", names));
        printDealer(dealer);
    }

    public void allPlayerKeepGoing() {
        for (Player player : playerList) {
            printPlayer(player);
        }
        System.out.print( "\n");
        for (Player player : playerList) {
            keepGoing(player);
        }
        System.out.print( "\n");
    }

    public void keepGoing(Player player) {
        while(checkMind(player)){
            printPlayer(player);
        }
    }

    public boolean checkMind(Player player) {
        String key;
        Scanner scan = new Scanner(System.in);
        System.out.println(player.getName() + "는한장의카드를더받겠습니까?(예는y,아니오는n)");
        key = scan.nextLine();
        if (key.contains("y")) {
            trying.getPlayerCard(player);
            return true;
        }
        return false;
    }

    public void printFirst(String names) {
        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public void printPlayer(Player player) {
        String deckString = String.join(",", player.getDeck());
        System.out.println(player.getName() + "카드: " + deckString);
    }

    public void printDealer(Dealer dealer) {
        String oneCard =  dealer.getDeck().get(0);
        System.out.println("딜러 카드: " + oneCard);
    }

}
