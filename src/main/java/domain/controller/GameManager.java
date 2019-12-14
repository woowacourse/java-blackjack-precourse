package domain.controller;

import domain.exception.InputException;
import domain.input.MoneyInput;
import domain.input.PlayerInput;
import domain.tools.ToolBox;
import domain.user.Player;
import domain.view.Announcer;

import java.util.List;

public class GameManager {
    PlayerInput input;
    ToolBox toolbox;
    Announcer announce;
    InputException inputException;
    MoneyInput moneyinput;
    Player[] players;

    public GameManager() {
        input = new PlayerInput();
        toolbox = new ToolBox();
        announce = new Announcer();
        inputException = new InputException();
        moneyinput = new MoneyInput();
    }

    private void playerStandBy(){
        createPlayer(createPlayerName());
    }

    private List<String> createPlayerName() {
        List<String> tmp;
        do {
            announce.announcePlayerInput();
            tmp = input.inputPlayerNames();
        } while (inputException.isZeroLength(tmp));
        return tmp;
    }

    private void createPlayer(List<String> nameList) {
        players = new Player[nameList.size()];
        int i = 0;
        for (String name : nameList) {
            announce.announceMoneyInput(name);
            players[i] = new Player(
                    name, moneyinput.inputBettingMoney());
        }
    }

    public void run() {
        playerStandBy();

    }
}
