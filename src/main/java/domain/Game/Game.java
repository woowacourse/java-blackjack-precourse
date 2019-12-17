package domain.Game;

import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Input input = new Input();
    private final Output output = new Output();
    private final List<Player> players = new ArrayList<>();
    private String[] playerNames;

    public void Play() {
        this.playerObjectCreate();
        output.StartCardState(players);
    }

    public void playerObjectCreate() {
        playerNames = input.playerNameInput();
        for (String playerName : playerNames) {
            Double bettingMoney = input.bettingMoneyInput(playerName);
            players.add(new Player(playerName, bettingMoney));
        }
    }
}
