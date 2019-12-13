package domain.game;

import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Set {
    private final ArrayList<Player> playerList = new ArrayList<Player>();

    public ArrayList<Player> gamePlayer(){
        String names = getPlayerNames();
        String[] nameList = splitPlayerNamse(names);
        createPlayers(nameList);
        return playerList;
    }

    public String getPlayerNames() {
        String names;
        Scanner scan = new Scanner(System.in);
        System.out.println("게임에참여할사람의이름을입력하세요.(쉼표기준으로분리)");
        names = scan.nextLine();
        return names;
    }

    public String[] splitPlayerNamse(String names) {
        String[] nameList;
        nameList = names.split(",");
        return nameList;
    }

    public double getBettingMoney(String name){
        double bettingMoney;
        Scanner scan = new Scanner(System.in);
        System.out.println(name + "의 배팅 금액은?");
        bettingMoney = scan.nextDouble();
        return bettingMoney;
    }

    public Player createPlayer(String name) {
        double bettingMoney = getBettingMoney(name);
        Player player = new Player(name,bettingMoney);
        return player;
    }

    public ArrayList<Player> createPlayers(String[] nameList) {
        for (int playerIndex = 0; playerIndex < nameList.length; playerIndex++) {
            playerList.add(createPlayer(nameList[playerIndex]));
        }
        return playerList;
    }

}
