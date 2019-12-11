import View.InputView;
import domain.user.Dealer;
import domain.user.GameMember;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackjackApplication {
    public static void main(String[] args) {
        String playerNames = InputView.inputPlayerNames();
        List<GameMember> gameMembers = createPlayersAndDealer(playerNames);

    }

    private static List<GameMember> createPlayersAndDealer(String playerNames) {
        List<GameMember> gameMembers = new ArrayList<>();
        gameMembers.addAll(createPlayersByNames(playerNames));
        gameMembers.add(createDealer());
        return gameMembers;
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
        Double bettingMoney = InputView.inputBettingMoney(playerName);
        return new Player(playerName, bettingMoney);
    }

    private static Dealer createDealer() {
        return new Dealer();
    }
}