package domain.game;

import domain.user.Dealer;
import domain.user.Player;

public class Scorer {
    public static int getDistanceToTarget(Dealer dealer) {
        return Constant.TARGET.getScore() - dealer.getScore();
    }

    public static int getDistanceToTarget(Player player) {
        return  Constant.TARGET.getScore() - player.getScore();
    }

    public static boolean isAboveTarget(Player player) {
        return player.getScore() >  Constant.TARGET.getScore();
    }

    public static boolean isAboveThreshold(Dealer dealer) {
        return dealer.getScore() >  Constant.DEALER_HIT.getScore();
    }
}
