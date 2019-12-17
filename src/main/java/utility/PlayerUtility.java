package utility;

import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import view.inputView;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtility {

    private static final String MESSAGE_NAME_CONDITION = "이름은 한 글자 이상이어야 합니다";
    private static final String PLAYER_NAME_DELIMITER = ",";
    private static final String MESSAGE_INPUT_INTEGER = "정수를 입력해주세요";

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
            System.out.println(MESSAGE_INPUT_INTEGER);
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
        ArrayList<Boolean> hasException = new ArrayList<>();
        for (String name : playerNames) {
            hasException.add(checkName(name));
        }
        return !hasException.contains(false);

    }

    private static Boolean checkName(String name) {
        if (name.length() < 1) {
            System.out.println(MESSAGE_NAME_CONDITION);
            return false;
        }
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
