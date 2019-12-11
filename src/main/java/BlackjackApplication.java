import View.InputView;
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
        String[] playerNamesSplit = playerNames.split(",");
        for (String playerName : playerNamesSplit) {
            Double bettingMoney = InputView.inputBettingMoney(playerName);
            gameMembers.add(new Player(playerName, bettingMoney));
        }
        return gameMembers;
    }
}