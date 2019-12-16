package system;

import domain.user.Player;
import view.InputView;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BlackjackSystem {
    private List<Player> playerList = new LinkedList<>();

    public void run() {
        setGame();
    }

    private void setGame() {
        String names = InputView.inputPlayerName();
        StringTokenizer st = splitPlayerName(names);
        setPlayer(st);
    }

    private StringTokenizer splitPlayerName(String names) {
        return new StringTokenizer(names, ",");
    }

    private void setPlayer(StringTokenizer st) {
        while (st.hasMoreTokens()) {
            String playerName = st.nextToken();
            double bettingMoney = InputView.inputPlayerMoney(playerName);
            addPlayer(new Player(playerName, bettingMoney));
        }
    }

    private void addPlayer(Player player) {
        playerList.add(player);
    }
    
    private int getRandomNumber(int range) {
        return (int) (Math.random() * range);
    }
}
