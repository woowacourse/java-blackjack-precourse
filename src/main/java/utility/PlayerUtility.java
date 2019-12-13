package utility;

import domain.user.Player;
import view.inputView;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtility {

    private static final String PLAYER_NAME_DELIMITER = ",";
    private static final int INITIAL_BETTING_MONEY = 0;

    public static List<Player> generatePlayers() {
        String[] playerNames = getPlayerNames();
        return makePlayers(playerNames);
    }

    private static String[] getPlayerNames() {
        String[] playerNames;

        do {
            String inputPlayerNames = inputView.inputPlayerNames();
            playerNames = inputPlayerNames.split(PLAYER_NAME_DELIMITER);
        } while (!validatePlayerNames(playerNames));

        return playerNames;
    }

    private static boolean validatePlayerNames(String[] playerNames) {
        return true;
    }

    private static List<Player> makePlayers(String[] playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Player player = new Player(playerName, INITIAL_BETTING_MONEY);
            players.add(player);
        }
        return players;
    }
}
