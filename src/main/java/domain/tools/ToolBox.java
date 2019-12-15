package domain.tools;

import domain.user.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToolBox {
    public List<String> splitPlayerName(String playerName) {
        return Arrays.asList(playerName.split(","));
    }

    public List<String> makePlayerNameList(Player[] players) {
        List<String> playerNameList = new ArrayList<>();
        for (Player player : players) {
                playerNameList.add(player.getName());
        }
        return playerNameList;
    }

    public String makePlayerNameString(Player[] players) {
        return String.join(",", makePlayerNameList(players));
    }


}
