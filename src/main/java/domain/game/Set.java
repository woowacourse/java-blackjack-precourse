/*
 * Set
 *
 * ver 1.0
 *
 * 2019.12.15
 *
 * CopyRight
 *
 */
package domain.game;


import domain.user.Player;
import java.util.ArrayList;
import java.util.Scanner;

public class Set {
    /*
     * Set 클래스 입니다.
     * 게임을 시작하기전에 게임에 필여힌
     * 사전준비를 담당하는 클래스입니다.
     */
    public static ArrayList<Player> gamePlayer() {
        String names = getPlayerNames();
        String[] nameList = splitPlayerNamse(names);
        ArrayList<Player> playerList = createPlayers(nameList);
        return playerList;
    }

    public static String getPlayerNames() {
        String names;
        Scanner scan = new Scanner(System.in);
        System.out.println("게임에참여할사람의이름을입력하세요.(쉼표기준으로분리)");
        names = scan.nextLine();
        return names;
    }

    public static String[] splitPlayerNamse(String names) {
        String[] nameList;
        nameList = names.split(",");
        return nameList;
    }

    public static double getBettingMoney(String name) {
        double bettingMoney;
        Scanner scan = new Scanner(System.in);
        System.out.println(name + "의 배팅 금액은?");
        bettingMoney = scan.nextDouble();
        return bettingMoney;
    }

    public static Player createPlayer(String name) {
        double bettingMoney = getBettingMoney(name);
        Player player = new Player(name,bettingMoney);
        return player;
    }

    public static ArrayList<Player> createPlayers(String[] nameList) {
        ArrayList<Player> playerList = new ArrayList<Player>();
        for (int playerIndex = 0; playerIndex < nameList.length; playerIndex++) {
            playerList.add(createPlayer(nameList[playerIndex]));
        }
        return playerList;
    }

}
