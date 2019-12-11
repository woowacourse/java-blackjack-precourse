import java.util.ArrayList;

import domain.user.Player;
import utils.InputHandler;
import view.InputView;

public class BlackJack {
    private ArrayList<Player> players = new ArrayList<>();

    void play() {
        setPlayers();
    }

    private void setPlayers() {
        for(String playerName : InputHandler.splitByComma(InputView.playerNames())) {
            int bettingMoney = InputView.bettingMoney(playerName);
            Player player = new Player(playerName, bettingMoney);
            players.add(player);
        }
    }
}
