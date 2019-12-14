package domain;

import domain.card.Card;
import domain.card.Symbol;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class Rule {
    private static final Card ace = new Card(Symbol.ACE, null);
    private static final int MAX_SCORE = 21;
    private static final int BIG_ACE_SCORE = 11;
    private static final int SMALL_ACE_SCORE = 1;
    private static final int DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE = BIG_ACE_SCORE - SMALL_ACE_SCORE;
    private static final int MIN_DEALER_SCORE = 17;

    public static void setScore(User user, Card card) {
        user.addScore(card.getScore());
        if (card.isSymbolEquals(ace)) {
            user.setIsAce(true);
        }
        if (user.isScoreGreaterThan(MAX_SCORE) && user.getIsBigAce()) {
            user.subtractScore(DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE);
            user.setIsBigAce(false);
        }
        if (!user.isScoreGreaterThan(MAX_SCORE - DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE) && user.getIsAce()) {
            user.addScore(DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE);
            user.setIsBigAce(true);
        }
    }

    public static boolean canDrawMore(Player player) {
        return !player.isScoreGreaterThan(MAX_SCORE) && !player.isBlackJack(MAX_SCORE);
    }

    public static boolean isDealDraw(Dealer dealer) {
        return !dealer.isScoreGreaterThan(MIN_DEALER_SCORE);
    }
}
