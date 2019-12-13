package domain.controller;

import domain.exception.InputException;
import domain.input.PlayerInput;
import domain.tools.ToolBox;
import domain.view.Announcer;

public class GameManager {
    PlayerInput input;
    ToolBox toolbox;
    Announcer announce;
    InputException inputException;

    public GameManager() {
        input = new PlayerInput();
        toolbox = new ToolBox();
        announce = new Announcer();
        inputException = new InputException();
    }

    private void playerStandBy(){

        do {
              announce.announcePlayerInput();
        } while (inputException.isZeroLength(
                        toolbox.splitPlayerName(
                        input.inputPlayerNames()
                 )));

    }

    public void run() {
        playerStandBy();

    }
}
