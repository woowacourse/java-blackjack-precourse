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
    private static final double TIE_MONEY_RATIO = 0.0;
    private static final int BURST_SCORE = -1;
    
    public static int getScore(ArrayList<Card> cards) {
        int score = cards.stream().mapToInt(Card::getScore).sum();
        if (score > BLACKJACK_SCORE) {
            return BURST_SCORE;
        }
        if (isAces(cards) && score <= BLACKJACK_SCORE - DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE) {
            return score + DIFFERENCE_BETWEEN_SMALL_AND_BIG_ACE;
        }
        return score;
    }

    private static boolean isAces(ArrayList<Card> cards) {
        return cards.stream().anyMatch(card -> card.isSymbolEquals(ace));
    }

    public static boolean canDrawCardMore(Player player) {
        int playerScore = getScore(player.showCards());
        return playerScore < BLACKJACK_SCORE && playerScore != BURST_SCORE;
    }

    public static boolean isDealerDrawCard(Dealer dealer) {
        int dealerScore = getScore(dealer.showCards());
        return dealerScore < MIN_DEALER_SCORE && dealerScore != BURST_SCORE;
    }

    public static double getDealerProfit(Dealer dealer, ArrayList<Player> players) {
        double dealerProfit = TIE_MONEY_RATIO;
        for (Player player : players) {
            dealerProfit -= getPlayerProfit(dealer, player);
        }
        return dealerProfit;
    }

    public static double getPlayerProfit(Dealer dealer, Player player) {
        int dealerScore = getScore(dealer.showCards());
        int playerScore = getScore(player.showCards());
        if (dealerScore == BURST_SCORE || isBlackJack(dealer) || isBlackJack(player)) {
            return calculateProfitInAbnormalCase(dealer, dealerScore, player);
        }
        return calculateProfit(player, playerScore, dealerScore);
    }

    private static boolean isBlackJack(User user) {
        int userScore = getScore(user.showCards());
        return userScore == BLACKJACK_SCORE && user.showCards().size() == FIRST_PLAYER_CARD_COUNTS;
    }

    private static double calculateProfitInAbnormalCase(Dealer dealer, int dealerScore, Player player) {
        if (dealerScore == BURST_SCORE) {
            return calculateProfitInDealerBurst(player);
        }
        if (isBlackJack(dealer)) {
            return calculateProfitInDealerBlackJack(player);
        }
        return player.getResultProfit(BLACKJACK_MONEY_RATIO);
    }

    private static double calculateProfit(Player player, int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return player.getResultProfit(WINNING_MONEY_RATIO);
        }
        if (playerScore == dealerScore) {
            return TIE_MONEY_RATIO;
        }
        return player.getResultProfit(LOSING_MONEY_RATIO);
    }

    private static double calculateProfitInDealerBurst(Player player) {
        if (isBlackJack(player)) {
            return player.getResultProfit(BLACKJACK_MONEY_RATIO);
        }
        if (getScore(player.showCards()) == BURST_SCORE) {
            return player.getResultProfit(LOSING_MONEY_RATIO);
        }
        return player.getResultProfit(WINNING_MONEY_RATIO);
    }

    private static double calculateProfitInDealerBlackJack(Player player) {
        if (isBlackJack(player)) {
            return TIE_MONEY_RATIO;
        }
        return player.getResultProfit(LOSING_MONEY_RATIO);
    }
}
