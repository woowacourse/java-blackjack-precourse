package domain.user;

import java.util.List;

public class Gamers {
    private List<Player> players;

    public Gamers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
