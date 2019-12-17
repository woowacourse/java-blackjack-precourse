package domain.gameElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import domain.user.Player;
import domain.user.User;

public class PlayersNaming {
    private Scanner scanner = new Scanner(System.in);

    public List<String> playerNaming() {
        String playerNames = "";
        List<String> playerNameList = new ArrayList<String>();
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리, 중복은 권장하지 않습니다.)");
            playerNames = scanner.nextLine().trim();
        } while (!new PlayersNaming().playerNamingCheck(playerNames));
        for (String player : playerNames.split(",")) {
            playerNameList.add(player.trim());
        }
        return playerNameList;
    }

    private boolean playerNamingCheck(String playerNames) {
        if (playerNamingCheckBlank(playerNames)) {
            return false;
        }
        return true;
    }

    private boolean playerNamingCheckBlank(String playerNames) {
        Set<Boolean> checkBlankSet = new HashSet<Boolean>();
        for (String playerName : playerNames.split(",")) {
            checkBlankSet.add(playerName.trim().isEmpty());
        }
        if (checkBlankSet.contains(true)) {
            return true;
        }
        return false;
    }

    public String getPlayerNamesALine(List<User> users) {
        StringBuilder playerNamesALineSB = new StringBuilder();
        for (int i = 1; i < users.size(); i++) {
            playerNamesALineSB.append(((Player) users.get(i)).getName());
            playerNamesALineSB.append(", ");
        }
        String playerNamesALine = playerNamesALineSB.toString();
        return playerNamesALine.substring(0, playerNamesALine.length() - 2);
    }
}
