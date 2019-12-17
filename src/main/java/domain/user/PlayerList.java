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

    public Player getPlayer(int playerNumber) {
        return playerList.get(playerNumber);
    }

    public int getSize() {
        return playerList.size();
    }

    public String toStringNames() {
        List<String> players = new ArrayList<String>();
        for (Player player : playerList) {
            players.add(player.getName());
        }
        return String.join(",", players);
    }

    public String toLog() {
        String printPlayerList = "";
        for (Player player : playerList) {
            printPlayerList += player.toLog() + "\n";
        }
        return printPlayerList;
    }

    @Override
    public String toString() {
        String printPlayerList = "";
        for (Player player : playerList) {
            printPlayerList += player.toString() + "\n";
        }
        return printPlayerList;
    }
}
