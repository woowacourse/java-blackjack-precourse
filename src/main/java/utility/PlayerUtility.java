package utility;

import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import view.inputView;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtility {

    private static final String PLAYER_NAME_DELIMITER = ",";
    public static final String MESSAGE_INPUT_INTEGER = "정수를 입력해주세요";

    public static Users generatePlayers() {
        String[] playerNames = getPlayerNames();
        ArrayList<Integer> playerBets = getPlayerBets(playerNames);
        return makePlayers(playerNames, playerBets);
    }

    private static ArrayList<Integer> getPlayerBets(String[] playerNames) {
        ArrayList<Integer> playerBets = new ArrayList<>();

        for (String playerName : playerNames) {
            playerBets.add(getPlayerBet(playerName));
        }
        return playerBets;
    }

    private static int getPlayerBet(String playerName) {
        String inputPlayerBet;
        do {
            inputPlayerBet = inputView.inputPlayerBet(playerName);
        } while (!validatePlayerBet(inputPlayerBet));

        return Integer.parseInt(inputPlayerBet);
    }

    private static boolean validatePlayerBet(String inputPlayerBet) {
        try {
            Integer.parseInt(inputPlayerBet);
            return true;
        } catch (Exception e) {
            System.out.println(MESSAGE_INPUT_INTEGER);    //추후 상세한 예외처리
            return false;
        }
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

    private static Users makePlayers(String[] playerNames, ArrayList<Integer> playerBets) {
        List<User> playersList = new ArrayList<>();
        for (int i = 0; i < playerNames.length; i++) {
            Player player = new Player(playerNames[i], playerBets.get(i));
            playersList.add(player);
        }
        return new Users(playersList);
    }
}
