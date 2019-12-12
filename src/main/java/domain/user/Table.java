package domain.user;

import java.util.LinkedList;
import java.util.List;

public class Table {
    List<Player> players;

    public Table() {
        players.add(new Player("딜러", 0));
    }

    public void addMember(Player player) {
        players.add(player);
    }
}
