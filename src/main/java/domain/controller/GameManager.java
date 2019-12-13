package domain.controller;

import domain.input.PlayerInput;
import domain.tools.ToolBox;
import domain.view.Announcer;

public class GameManager {
    PlayerInput input;
    ToolBox toolbox;
    Announcer announce;

    public GameManager() {
        input = new PlayerInput();
        toolbox = new ToolBox();
        announce = new Announcer();
    }

    private void playerStandBy(){
        announce.announcePlayerInput();
        toolbox.splitPlayerName(input.inputPlayerNames());
    }

    public void run() {
        playerStandBy();

    }
}
