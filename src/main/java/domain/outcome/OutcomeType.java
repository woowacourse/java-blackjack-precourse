package domain.outcome;

import domain.card.Score;

// TODO : eunm도 comparable할 수 있는지 확인하자
public enum OutcomeType {
    INIT_USER_BLACKJACK(0.5),
    INIT_BOTH_BLACKJACK(0),
    DRAW(0),
    USER_WIN(1.0),
    USER_LOSE(-1.0);

    private final double multipleValue;

    OutcomeType(double multipleValue) {
        this.multipleValue = multipleValue;
    }

    public double getMultipleValue() {
        return multipleValue;
    }

    public static OutcomeType calcurateBlackJackBenefit(
            boolean isDealerBlackJack
    ) {
        if (isDealerBlackJack) {
            return OutcomeType.INIT_BOTH_BLACKJACK;
        }
        return OutcomeType.INIT_USER_BLACKJACK;
    }

    public static OutcomeType decideWinOrLose(Score userScore, Score dealerScore) {
        if (userScore.isBust() || !userScore.isBigBy(dealerScore.getScore())) {
            return OutcomeType.USER_LOSE;
        }
        if (dealerScore.isBust() && userScore.isBigBy(dealerScore.getScore())) {
            return OutcomeType.USER_WIN;
        }
        if (userScore.isSameTo(dealerScore.getScore())) {
            return OutcomeType.DRAW;
        }
        throw new IllegalStateException("승패가 결정되지 않았습니다.");
    }
}
