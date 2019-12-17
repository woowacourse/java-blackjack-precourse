package domain.user;

import java.util.List;

import exception.Validator;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        Validator.validatePlayersSize(players.size());
        Validator.validateDuplicatedName(players);
        this.players = players;
    }
}
