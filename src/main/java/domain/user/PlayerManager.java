package domain.user;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private final ArrayList<Player> players = new ArrayList<>();

    public PlayerManager() {
        /* Empty */
    }

    public void add(Player other) {
        players.add(other);
    }

    public void addAll(List<Player> others) {
        players.addAll(others);
    }

    public String bettingInfos() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player: players) {
            stringBuilder.append(player.bettingInfo());
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }
}
