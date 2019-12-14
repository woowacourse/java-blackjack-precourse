package domain;

import domain.card.Card;
import domain.card.Symbol;
import domain.user.User;

public class Rule {
    private static final Card ace = new Card(Symbol.ACE, null);
    private static final int MAX_SCORE = 21;
    private static final int BIG_ACE_SCORE = 11;
    private static final int SMALL_ACE_SCORE = 1;
    private static final int DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE = BIG_ACE_SCORE - SMALL_ACE_SCORE;

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

    public static boolean canDrawMore(User user) {
        return !user.isScoreGreaterThan(MAX_SCORE) && !user.isBlackJack(MAX_SCORE);
    }
}
