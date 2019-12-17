package domain.game;

import domain.user.Dealer;
import domain.user.GameParticipant;
import domain.user.Player;

import java.util.ArrayList;

public class RevenueCalculator {
    private final static double MULTIPLE_OF_BATTING_MONEY_WHEN_BLACKJACK = 1.5;
    private final static double FINAL_REVENUE_WHEN_DEALER_AND_PLAYER_BLACKJACK = 0.0;

    private ArrayList<Double> finalRevenueListOfGameParticipant = new ArrayList<Double>();
    private ArrayList<GameParticipant> participants;
    private Dealer dealer;
    private double finalRevenueOfDealer = 0.0;

    public RevenueCalculator(ArrayList<GameParticipant> participants, Dealer dealer) {
        this.participants = participants;
        this.dealer = dealer;
    }

    public ArrayList<Double> calculateFinalRevenueOfPlayer() {
        for (GameParticipant p : participants) {
            if (p == dealer) {
                finalRevenueListOfGameParticipant.add(finalRevenueOfDealer);
                break;
            }
            calculateFinalRevenueOfPlayerInBlackjack(p);
            calculateFinalRevenueOfPlayerNotInBlackjack(p);
        }

        return finalRevenueListOfGameParticipant;
    }

    private void calculateFinalRevenueOfPlayerInBlackjack(GameParticipant p) {
        if (p.isBlackjack() && dealer.isBlackjack()) {
            finalRevenueListOfGameParticipant.add(FINAL_REVENUE_WHEN_DEALER_AND_PLAYER_BLACKJACK);
        }

        if (p.isBlackjack() && !dealer.isBlackjack()) {
            finalRevenueListOfGameParticipant.add(((Player)p).getBattingMoney() * MULTIPLE_OF_BATTING_MONEY_WHEN_BLACKJACK);
            finalRevenueOfDealer -= (((Player)p).getBattingMoney() * MULTIPLE_OF_BATTING_MONEY_WHEN_BLACKJACK);
        }
    }

    private void calculateFinalRevenueOfPlayerNotInBlackjack(GameParticipant p) {
        if (p.isBlackjack()) {
            return;
        }

        if (isWinner(p) || (!isWinner(p) && dealer.isBust() && !p.isBust())) {
            finalRevenueListOfGameParticipant.add(((Player)p).getBattingMoney());
            finalRevenueOfDealer -= ((Player)p).getBattingMoney();
        }

        if (!isWinner(p) && !dealer.isBust()) {
            finalRevenueListOfGameParticipant.add(-((Player)p).getBattingMoney());
            finalRevenueOfDealer += ((Player)p).getBattingMoney();
        }
    }

    private boolean isWinner(GameParticipant p) {
        return p.getSumOfCardScore() == findMaxScore();
    }

    private double findMaxScore() {
        double maxSumOfScore = 0;
        for (GameParticipant p : participants) {
            if (p.isBust()) continue;
            if (p.getSumOfCardScore() > maxSumOfScore) maxSumOfScore = p.getSumOfCardScore();
        }
        return maxSumOfScore;
    }

}

