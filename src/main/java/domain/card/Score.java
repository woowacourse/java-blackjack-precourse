package domain.card;

public class Score {
    private static final int SCORE_MIN = 0;
    private static final int BLACKJACK_SCORE = 21;
    private static final int TEN = 10;

    public static final Score ZERO = new Score(0);

    private final int score;

    public Score(int score) {
        if (score < SCORE_MIN) {
            throw new IllegalArgumentException("점수는 0이상이어야 합니다.");
        }
        this.score = score;
    }

    public Score plusTenIfNotBust() {
        int plusTenScore = this.score + TEN;
        if (plusTenScore > BLACKJACK_SCORE) {
            return this;
        }
        return new Score(plusTenScore);
    }

    public int getScore() {
        return this.score;
    }

    public Score plus(Score score) {
        return new Score(this.score + score.getScore());
    }

    public boolean isBlackJack() {
        return this.score == BLACKJACK_SCORE;
    }

    public boolean isBust() {
        return this.score > BLACKJACK_SCORE;
    }

    public boolean isBiggerThan(Score score) {
        return this.score > score.getScore();
    }

    public boolean isLessThan(Score score) {
        return this.score < score.getScore();
    }
}
