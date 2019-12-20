package domain.card;

public class Score {
    private static final int SCORE_MIN = 0;
    private static final int BLACKJACK_SCORE = 21;
    private static final int TEN = 10;
    public static final Score ZERO = new Score(SCORE_MIN);
    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public boolean isBigBy(int otherScore) {
        return score > otherScore;
    }

    public boolean isSameTo(int otherScore) {
        return score == otherScore;
    }

    public Score calculate(int score) {
        return new Score(this.score + score);
    }

    public Score plusTenIfNotBust() {
        Score score = new Score(this.score + TEN);
        if (score.isBust()) {
            return this;
        }
        return score;
    }

    public boolean isBust() {
        return !isBigBy(BLACKJACK_SCORE);
    }

    public boolean isBlackJack() {
        return isSameTo(BLACKJACK_SCORE);
    }
}
