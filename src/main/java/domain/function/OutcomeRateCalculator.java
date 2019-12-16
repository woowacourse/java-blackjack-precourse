package domain.function;

import domain.user.BlackjackUserResult;

public class OutcomeRateCalculator {
    private final BlackjackUserResult dealerResult;
    private final BlackjackUserResult playerResult;

    public OutcomeRateCalculator(BlackjackUserResult dealerResult, BlackjackUserResult playerResult) {
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public double getPlayerOutcomeRate() {
        Outcome playerOutcomeWithBlackjack = getPlayerOutcomeWithBlackjack();
        if (!playerOutcomeWithBlackjack.isNothing()) {
            return playerOutcomeWithBlackjack.getRate();
        }
        Outcome playerOutcomeWithBust = getPlayerOutcomeWithBust();
        return playerOutcomeWithBust.getRate();
    }

    private Outcome getPlayerOutcomeWithBlackjack() {
        if (dealerResult.isBlackjack() && playerResult.isBlackjack()) {
            return Outcome.DRAW;
        }

        if (dealerResult.isNotBlackjack() && playerResult.isBlackjack()) {
            return Outcome.BLACKJACK_WIN;
        }

        if (dealerResult.isBlackjack() && playerResult.isNotBlackjack()) {
            return Outcome.LOSE;
        }

        return Outcome.NOTHING;
    }

    private Outcome getPlayerOutcomeWithBust() {
        if (playerResult.isBust()) {
            return Outcome.LOSE;
        }
        if (dealerResult.isBust()) {
            return Outcome.WIN;
        }
        return getPlayerOutcomeWithCompare();
    }

    private Outcome getPlayerOutcomeWithCompare() {
        if (playerResult.isBiggerThan(dealerResult)) {
            return Outcome.WIN;
        } else if (playerResult.isSameTo(dealerResult)) {
            return Outcome.DRAW;
        } else {
            return Outcome.LOSE;
        }
    }

}
