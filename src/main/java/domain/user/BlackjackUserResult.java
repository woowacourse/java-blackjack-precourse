package domain.user;

public class BlackjackUserResult {
    public static final int PERFECT_SCORE = 21;
    private final int cardCount;
    private final int totalScore;

    public BlackjackUserResult(int cardCount, int totalScore) {
        this.cardCount = cardCount;
        this.totalScore = totalScore;
    }

    public boolean isPerfectScore() {
        return totalScore == PERFECT_SCORE;
    }

    public boolean isBlackjack() {
        return cardCount == 2 && isPerfectScore();
    }

    public boolean isNotBlackjack() {
        return cardCount != 2 || totalScore != PERFECT_SCORE;
    }

    public boolean isBust() {
        return PERFECT_SCORE < totalScore;
    }

    public boolean isBiggerThan(BlackjackUserResult blackjackUserResult) {
        return totalScore > blackjackUserResult.totalScore;
    }

    public boolean isSameTo(BlackjackUserResult blackjackUserResult) {
        return totalScore == blackjackUserResult.totalScore;
    }
}
