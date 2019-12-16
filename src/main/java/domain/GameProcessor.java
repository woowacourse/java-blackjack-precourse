package domain;

import domain.user.Player;

import java.util.ArrayList;

public class GameProcessor {
    private static ArrayList<Player> playerArray = new ArrayList<Player>();

    static void initGame(ArrayList<String> playerNamesArray) {
        for (String playerName : playerNamesArray) {
            playerArray.add(new Player(playerName, 1000));
        }

        for (int i = 0; i < playerArray.size(); i++) {
            System.out.println(playerArray.get(i).getName() + ", " + playerArray.get(i).getBettingMoney());
        }
    }
}
