package domain.user;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {

    private List<Player> playerList = new ArrayList<Player>();

    public void addPlayer(Player player) {
        playerList.add(player);
    }

}
