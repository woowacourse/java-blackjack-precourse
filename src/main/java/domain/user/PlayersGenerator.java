package domain.user;

import java.util.ArrayList;
import java.util.List;

import exception.Validator;
import view.InputView;
import view.OutputView;

public class PlayersGenerator {
    private static final String COMMA = ",";

    public static Players createByInput() {
        String input = InputView.inputPlayersName();
        String[] playerNames = input.split(COMMA);
        try {
            Validator.checkPlayerNames(playerNames);
            return createPlayers(playerNames);
        } catch (IllegalArgumentException e) {
            OutputView.println(e.getMessage());
            return createByInput();
        }
    }

    private static Players createPlayers(String[] playerNames) {
        List<Player> players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(createPlayerByBettingMoney(name));
        }
        return new Players(players);
    }

    private static Player createPlayerByBettingMoney(String name) {
        int bettingMoney = InputView.inputBettingMoney(name);
        try {
            return new Player(name, (double) bettingMoney);
        } catch (IllegalArgumentException e) {
            OutputView.println(e.getMessage());
            return createPlayerByBettingMoney(name);
        }
    }

}
