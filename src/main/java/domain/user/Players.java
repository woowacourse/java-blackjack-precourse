package domain.user;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
