package domain.game;

import domain.card.Card;
import domain.card.Symbol;
import domain.user.Dealer;
import domain.user.Player;

public class Scorer {
    private static final int TARGET_SCORE = 21;
    private static final int ALTERNATIVE_ACE_SCORE = 11;
    private static final int DEALER_THRESHOLD = 16;

    public static int getDistanceToTarget(Dealer dealer) {
        return TARGET_SCORE - sumCardScores(dealer);
    }

    public static int getDistanceToTarget(Player player) {
        return TARGET_SCORE - sumCardScores(player);
    }

    public static boolean isAboveTarget(Player player) {
        return sumCardScores(player) > TARGET_SCORE;
    }

    public static boolean isAboveThreshold(Dealer dealer) {
        return sumCardScores(dealer) > DEALER_THRESHOLD;
    }

    public static int sumCardScores(Dealer dealer) {
        int scoreSum = dealer.getCards().stream()
                .map(Card::getSymbol)
                .mapToInt(Symbol::getScore)
                .sum();
        if (dealer.hasAce()) {
            return adjustAce(scoreSum);
        }
        return scoreSum;
    }

    public static int sumCardScores(Player player) {
        int scoreSum = player.getCards().stream()
                .map(Card::getSymbol)
                .mapToInt(Symbol::getScore)
                .sum();
        if (player.hasAce()) {
            return adjustAce(scoreSum);
        }
        return scoreSum;
    }

    private static int adjustAce(int scoreSum) {
        int adjustment = ALTERNATIVE_ACE_SCORE - Symbol.ACE.getScore();
        if (scoreSum + adjustment <= TARGET_SCORE) {
            return scoreSum + adjustment;
        }
        return scoreSum;
    }
}
