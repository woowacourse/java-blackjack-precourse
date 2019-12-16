package domain;

import java.util.ArrayList;

import domain.card.Card;
import domain.card.Symbol;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class Rule {
    private static final Card ace = new Card(Symbol.ACE, null);
    private static final int BLACKJACK_SCORE = 21;
    private static final int BIG_ACE_SCORE = 11;
    private static final int SMALL_ACE_SCORE = 1;
    private static final int DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE = BIG_ACE_SCORE - SMALL_ACE_SCORE;
    private static final int MIN_DEALER_SCORE = 17;
    private static final int FIRST_PLAYER_CARD_COUNTS = 2;
    private static final double WINNING_MONEY_RATIO = 1.0;
    private static final double LOSING_MONEY_RATIO = -1.0;
    private static final double BLACKJACK_MONEY_RATIO = 1.5;
    private static final int BURST_SCORE = -1;
    
    public static void setScore(User user, Card card) {
        if (user.isScoreEquals(BURST_SCORE)) {
            return;
        }
        user.addScore(card.getScore());
        if (card.isSymbolEquals(ace)) {
            user.setIsAce(true);
        }
        if (user.isScoreGreaterThan(BLACKJACK_SCORE) && user.getIsBigAce()) {
            user.subtractScore(DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE);
            user.setIsBigAce(false);
            return;
        }
        if (!user.isScoreGreaterThan(BLACKJACK_SCORE - DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE) && user.getIsAce()) {
            user.addScore(DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE);
            user.setIsBigAce(true);
            return;
        }
        if (user.isScoreGreaterThan(BLACKJACK_SCORE) && !user.getIsBigAce()) {
            user.setIsBurst(true);
            user.setScore(BURST_SCORE);
        }
    }

    public static boolean canDrawMore(Player player) {
        if (player.getIsBurst()) {
            return false;
        }
        return !player.isScoreGreaterThan(BLACKJACK_SCORE) && !player.isScoreEquals(BLACKJACK_SCORE);
    }

    public static boolean isDealerDraw(Dealer dealer) {
        return !dealer.isScoreGreaterThan(MIN_DEALER_SCORE) && !dealer.getIsBurst();
    }

    public static double getDealerProfit(Dealer dealer, ArrayList<Player> players) {
        double dealerProfit = 0.0;
        for (Player player : players) {
            dealerProfit -= getPlayerProfit(dealer, player);
        }
        return dealerProfit;
    }

    private static boolean getIsBlackJack(User user) {
        return user.isScoreEquals(BLACKJACK_SCORE) && user.showCards().size() == FIRST_PLAYER_CARD_COUNTS;
    }

    public static double getPlayerProfit(Dealer dealer, Player player) {
        if (dealer.isScoreEquals(BURST_SCORE)) {
            return getProfitInDealerBurst(player);
        }
        if (getIsBlackJack(dealer)) {
            return getProfitInBlackJack(player);
        }
        if (getIsBlackJack(player)) {
            return player.getResultProfit(BLACKJACK_MONEY_RATIO);
        }
        if (player.isScoreGreaterThan(dealer.getScore())) {
            return player.getResultProfit(WINNING_MONEY_RATIO);
        }
        if (player.isScoreEquals(dealer.getScore())) {
            return 0.0;
        }
        return player.getResultProfit(LOSING_MONEY_RATIO);
    }

    private static double getProfitInBlackJack(Player player) {
        if (getIsBlackJack(player)) {
            return 0.0;
        }
        return player.getResultProfit(LOSING_MONEY_RATIO);
    }

    private static double getProfitInDealerBurst(Player player) {
        if (player.isScoreEquals(BURST_SCORE)) {
            return player.getResultProfit(LOSING_MONEY_RATIO);
        }
        return player.getResultProfit(WINNING_MONEY_RATIO);
    }
}
