package Controller;

import View.InputView;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class UsersFactory {
    public static List<User> createUsers(String playerNames) {
        List<User> users = new ArrayList<>();
        users.addAll(createPlayersByNames(playerNames));
        users.add(createDealer());
        return users;
    }

    private static List<Player> createPlayersByNames(String playerNames) {
        List<Player> players = new ArrayList<>();

        String[] playerNamesSplit = playerNames.split(",");
        for (String playerName : playerNamesSplit) {
            createPlayerByName(playerName);
        }

        return players;
    }

    private static Player createPlayerByName(String playerName) {
        double bettingMoney = InputView.inputBettingMoney(playerName);
        return new Player(playerName, bettingMoney);
    }

    private static Dealer createDealer() {
        return new Dealer();
    }
}
