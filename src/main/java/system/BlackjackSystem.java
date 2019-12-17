package system;

import domain.user.Dealer;
import domain.user.Player;

import java.util.LinkedList;
import java.util.List;

public class BlackjackSystem {
    private Dealer dealer;
    private List<Player> playerList;
    private SettingSystem settingSystem;

    public BlackjackSystem() {
        dealer = new Dealer();
        playerList = new LinkedList<>();
    }

    public void run() {
        settingSystem = new SettingSystem(dealer, playerList);
        settingSystem.setGame();

        InGameSystem inGameSystem = new InGameSystem(dealer, playerList, settingSystem);
        inGameSystem.play();

        ResultSystem resultSystem = new ResultSystem(dealer, playerList);
        resultSystem.getResult();
    }
}
