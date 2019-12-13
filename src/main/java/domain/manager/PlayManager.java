package domain.manager;

import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayManager {
    public final List<Gamer> gamers = new ArrayList<>();
    private final int dealerIndex = 0;

    public PlayManager(List<String> playerNameList, List<Integer> bettingMoneyList) {
        gamers.add(new Dealer());
        this.getPlayers(playerNameList, bettingMoneyList);
    }

    private void getPlayers(List<String> playerNameList, List<Integer> bettingMoneyList) {
        for (int i = 0; i < playerNameList.size(); i++) {
            gamers.add(new Player(playerNameList.get(i), bettingMoneyList.get(i)));
        }
    }
}
