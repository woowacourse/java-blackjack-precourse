package domain.game;

public class Winner {
    public static int getDistanceToTarget(int userScore) {
        return Constant.TARGET.getScore() - userScore;
    }
}
