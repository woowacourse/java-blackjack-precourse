package domain.gameElement;
import java.util.*;

public class PlayersNaming {

    public List<String> playerNaming() {
        String playerNames = "";
        List<String> playerNameList = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리, 중복은 권장하지 않습니다.)");
            playerNames = scanner.nextLine().trim();
        } while (!new PlayersNaming().playerNamingCheck(playerNames));
        for (String player : playerNames.split(",")) {
            playerNameList.add(player.trim());
        }
        return playerNameList;
    }

    public boolean playerNamingCheck(String playerNames) {
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
}
