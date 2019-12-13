package domain.user;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {

    private List<Player> playerList = new ArrayList<Player>();

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public String toStringNames() {
        List<String> players = new ArrayList<String>();
        for (Player player : playerList) {
            players.add(player.getName());
        }
        return String.join(",", players);
    }

}
