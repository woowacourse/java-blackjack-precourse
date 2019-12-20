package domain.user;

/**
 * 패의 점수를 의미하는 객체
 */
public class Score {
    public static final int SCORE_MIN = 0;
    public static final int BLACKJACK_SCORE = 21;
    public static final int TEN = 10;
    public static final int DEALER_BASIS = 17;

    private final int score;

    public Score(int score) {
        if (score < SCORE_MIN) {
            throw new IllegalArgumentException("점수는 0 이상이어야 합니다.");
        }
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Score plus(int score) {
        return new Score(this.score + score);
    }

    public Score plus(Score score) {
        return new Score(this.score + score.getScore());
    }

    public Score plusTenIfNotBust() {
        Score score = new Score(this.score + TEN);
        if (score.isBust()) {
            return this;
        }
        return score;
    }

    private boolean isBust() {
        return this.score > BLACKJACK_SCORE;
    }
}
