package domain;

import domain.user.Player;
import domain.user.PlayerInputData;

import java.util.List;

public class BlackJackGame {
    private static List<Player> playerList;

    public static void main(String[] args) {
        PlayerInputData inputData = new PlayerInputData();
        playerList = inputData.getPlayerList();
    }

}
