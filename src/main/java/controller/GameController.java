package controller;

import java.util.ArrayList;

import domain.user.Dealer;
import domain.user.Player;
import io.InputSystem;
import io.OutputSystem;

public class GameController {

    private Dealer dealer;
    private ArrayList<Player> players;
    private InputSystem inputSystem;

    public GameController() {
        players = new ArrayList<>();
        inputSystem = new InputSystem();
    }

    public void initGame() {
        OutputSystem.printGetName();
        String names[] = commaNameSlice(inputSystem.inputUserName());
        for (int i = 0; i < names.length; i++) {
            OutputSystem.printBettingPrice(player);
            players.add(new Player(names[i], inputSystem.inputBettingPirce()));
        }
    }

    private String[] commaNameSlice(String names) {
        return names.split(",");
    }

}