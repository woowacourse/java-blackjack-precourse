package Controller;

import View.InputController;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class UsersFactory {
    private static final String DELIMITER_FOR_SPLIT = ",";

    public static List<User> createUsers(String playerNames) {
        List<User> users = new ArrayList<>();
        users.addAll(createPlayersByNames(playerNames));
        users.add(createDealer());
        return users;
    }

    private static List<Player> createPlayersByNames(String playerNames) {
        List<Player> players = new ArrayList<>();

        String[] playerNamesSplit = playerNames.split(DELIMITER_FOR_SPLIT);
        for (String playerName : playerNamesSplit) {
            players.add(createPlayerByName(playerName));
        }

        return players;
    }

    private static Player createPlayerByName(String playerName) {
        double bettingMoney = InputController.askBettingMoney(playerName);
        return new Player(playerName, bettingMoney);
    }

    private static Dealer createDealer() {
        return new Dealer();
    }
}
