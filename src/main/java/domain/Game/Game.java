package domain.Game;

import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Input input = new Input();
    private final List<Player> players = new ArrayList<>();
    private String[] playerNames;

    public void Play() {
        this.playerObjectCreate();
    }

    public void playerObjectCreate() {
        playerNames = input.playerNameInput();
        for (String playerName : playerNames) {
            Double bettingMoney = input.bettingMoneyInput(playerName);
            players.add(new Player(playerName, bettingMoney));
        }
    }
}
