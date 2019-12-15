package domain.game;

import domain.card.Card;
import domain.card.Symbol;
import domain.user.Dealer;
import domain.user.Player;

public class Scorer {
    private static final int TARGET_SCORE = 21;
    private static final int ALTERNATIVE_ACE_SCORE = 11;

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
