package domain.revenuecalculator;

import domain.scoring.Scoring;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.List;

public class RevenueCalculator {
    /* cases */
    private static final int PLAYER_BLACKJACK_AND_DEALER_NOT_BLACKJACK = 0;
    private static final int DEALER_BUST_OR_PLAYER_LARGER_THAN_DEALER = 1;
    private static final int PLAYER_EQUALS_DEALER = 2;
    private static final int PLAYER_BUST_OR_DEALER_LARGER_THAN_PLAYER = 3;

    private static final double PLAYER_MAX_INTEREST = 1.5;

    public static double getDealerRevenue(Dealer dealer, Player player) {
        int caseNumber = findWhichCase(getScore(dealer), getScore(player));

        if (caseNumber == PLAYER_BLACKJACK_AND_DEALER_NOT_BLACKJACK) {
            return -1 * PLAYER_MAX_INTEREST * player.getBettingMoney();
        }

        if (caseNumber == DEALER_BUST_OR_PLAYER_LARGER_THAN_DEALER) {
            return -1 * player.getBettingMoney();
        }

        if (caseNumber == PLAYER_EQUALS_DEALER) {
            return 0;
        }

        return player.getBettingMoney();
    }

    private static int getScore(User user) {
        return Scoring.getTotalScore(user.openAllCards());
    }

    public static double getPlayerRevenue(Dealer dealer, Player player) {
        int caseNumber = findWhichCase(getScore(dealer), getScore(player));

        if (caseNumber == PLAYER_BLACKJACK_AND_DEALER_NOT_BLACKJACK) {
            return PLAYER_MAX_INTEREST * player.getBettingMoney();
        }

        if (caseNumber == DEALER_BUST_OR_PLAYER_LARGER_THAN_DEALER) {
            return player.getBettingMoney();
        }

        if (caseNumber == PLAYER_EQUALS_DEALER) {
            return 0;
        }

        return -1 * player.getBettingMoney();
    }

    private static int findWhichCase(int dealerScore, int playerScore) {

        if (playerScore == Scoring.BLACKJACK_MAX_SCORE
                && dealerScore != Scoring.BLACKJACK_MAX_SCORE) {
            return PLAYER_BLACKJACK_AND_DEALER_NOT_BLACKJACK;
        }

        if (playerScore > dealerScore || dealerScore > Scoring.BLACKJACK_MAX_SCORE) {
            return DEALER_BUST_OR_PLAYER_LARGER_THAN_DEALER;
        }

        if (playerScore == dealerScore) {
            return PLAYER_EQUALS_DEALER;
        }

        return PLAYER_BUST_OR_DEALER_LARGER_THAN_PLAYER;
    }

    public static double getTotalRevenueOfDealer(Dealer dealer, List<Player> players) {
        double ret = 0;

        for (Player player : players) {
            ret += getDealerRevenue(dealer, player);
        }

        return ret;
    }
}
