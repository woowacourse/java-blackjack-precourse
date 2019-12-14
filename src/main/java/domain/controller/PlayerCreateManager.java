package domain.controller;

import domain.exception.InputException;
import domain.input.MoneyInput;
import domain.input.PlayerInput;
import domain.tools.ToolBox;
import domain.user.Player;
import domain.view.Announcer;

import java.util.List;

public class PlayerCreateManager {
    CardManager cardManager = new CardManager();
    PlayerInput input;
    ToolBox toolbox;
    Announcer announce;
    InputException inputException;
    MoneyInput moneyinput;
    Player[] players;

    final static int MAKE_TWO_CARD = 2;

    public PlayerCreateManager() {
        CardManager cardManager = new CardManager();
        input = new PlayerInput();
        toolbox = new ToolBox();
        announce = new Announcer();
        inputException = new InputException();
        moneyinput = new MoneyInput();
    }

    public void playerStandBy(){
        createPlayer(createPlayerName());
    }

    private void createPlayer(List<String> nameList) {
        players = new Player[nameList.size()];
        int i = 0;

        for (String name : nameList) {
            announce.announceMoneyInput(name);
            players[i] = new Player(
                    name, moneyinput.inputBettingMoney());
            cardManager.giveCard(players[i], MAKE_TWO_CARD);
        }
    }

    private List<String> createPlayerName() {
        List<String> tmp;

        do {
            announce.announcePlayerInput();
            tmp = input.inputPlayerNames();
        } while (inputException.isZeroLength(tmp));

        return tmp;
    }
}
