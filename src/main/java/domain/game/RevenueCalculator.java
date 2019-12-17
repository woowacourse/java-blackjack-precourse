package domain.game;

import domain.user.Dealer;
import domain.user.GameParticipant;

import java.util.ArrayList;

public class RevenueCalculator {

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
            finalRevenueListOfGameParticipant.add(0.0);
        }

        if (p.isBlackjack() && !dealer.isBlackjack()) {
            finalRevenueListOfGameParticipant.add(p.getSumOfCardScore() * 1.5);
            finalRevenueOfDealer -= (p.getSumOfCardScore() * 1.5);
        }
    }

    private void calculateFinalRevenueOfPlayerNotInBlackjack(GameParticipant p) {
        if (p.isBlackjack()) {
            return;
        }

        if (isWinner(p) || (!isWinner(p) && dealer.isBust() && !p.isBust())) {
            finalRevenueListOfGameParticipant.add(p.getSumOfCardScore());
            finalRevenueOfDealer -= p.getSumOfCardScore();
        }

        if (!isWinner(p) && !dealer.isBust()) {
            finalRevenueListOfGameParticipant.add(-p.getSumOfCardScore());
            finalRevenueOfDealer += p.getSumOfCardScore();
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

