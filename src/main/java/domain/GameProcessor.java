package domain;

import domain.user.Player;

import java.util.ArrayList;

public class GameProcessor {
    private static ArrayList<Player> playersArray = new ArrayList<Player>();

    static void addPlayers(ArrayList<String> playerNamesArray) {
        for (String playerName : playerNamesArray) {
            Double playerMoney = InputProcessor.getPlayerMoney(playerName);
            playersArray.add(new Player(playerName, playerMoney));
        }

        for (int i = 0; i < playersArray.size(); i++) {
            System.out.println(playersArray.get(i).getName() + ", " + playersArray.get(i).getBettingMoney());
        }
    }
}
