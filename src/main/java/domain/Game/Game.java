package domain.Game;

import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Input input = new Input();
    private final List<Player> players = new ArrayList<>();
    private String[] playerNames;

    public void Play() {
        playerNames = input.playerNameInput();
    }
}
