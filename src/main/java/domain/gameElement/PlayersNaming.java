package domain.gameElement;
import java.util.Set;
import java.util.HashSet;

public class PlayersNaming {
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
