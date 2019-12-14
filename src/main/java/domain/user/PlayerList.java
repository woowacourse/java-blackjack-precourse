package domain.user;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {

    private List<Player> playerList;

    public PlayerList() {
        playerList = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        this.playerList.add(player);
    }

    public String toStringNames() {
        List<String> players = new ArrayList<String>();
        for (Player player : playerList) {
            players.add(player.getName());
        }
        return String.join(",", players);
    }

    @Override
    public String toString() {
        String printPlayerList = "";
        for (Player player : playerList) {
            printPlayerList += player.getName() + ":" + player.getBettingMoney() + "\n";
        }
        return printPlayerList;
    }
}
