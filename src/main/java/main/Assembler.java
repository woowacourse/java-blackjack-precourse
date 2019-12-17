package main;

import application.FlowController;
import application.domain.card.CardSupplier;
import application.domain.game.Game;
import application.domain.game.Result;
import application.domain.user.Dealer;
import application.domain.user.Player;
import application.domain.user.Users;
import application.util.IndexGenerator;
import application.view.Input;

import java.util.*;

public class Assembler {
    private static final String USER_NAME_SPLITTER = ",";

    public static Users getUsersObject() {
        String userNames = Input.getNamesFromConsole();
        List<Player> players = getPlayers(userNames);
        return new Users(players, new Dealer());
    }

    private static List<Player> getPlayers(String names) {
        List<Player> players = new ArrayList<>();
        for (String name : names.split(USER_NAME_SPLITTER)) {
            double playerBettingSize = Input.getBettingMoney(name);
            players.add(new Player(name, playerBettingSize));
        }
        return players;
    }

    public static Game getCardGameObject(Users users, IndexGenerator generator) {
        CardSupplier cardSupplier = new CardSupplier(generator);
        return new Game(users, cardSupplier);
    }

    public static FlowController getGameFlow(Game blackJackGame, Users blackJackUsers) {
        return new FlowController(blackJackGame, new Result(blackJackUsers));
    }
}
