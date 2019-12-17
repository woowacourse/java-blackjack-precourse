package domain.game;

import domain.user.Player;

import java.util.List;

public class Winner {
    private List<Player> winners;

    public static int getDistanceToTarget(int userScore) {
        return Constant.TARGET.getScore() - userScore;
    }
}
