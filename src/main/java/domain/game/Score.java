package domain.game;

public class Score {
    private static final int SCORE_MIN = 0;
    private static final int BLACKJACK_SCORE = 21;
    private static final int TEN = 10;

    public static final Score ZERO = new Score(0);

    private final int score;

    public Score(int score){
        if(score < SCORE_MIN) {
            throw new IllegalArgumentException("점수는 0 이상이어야 합니다.");
        }
        this.score = score;
    }

    public Score calculate(int score){
        return new Score(this.score + score);
    }

    public Score plusTenIfNotBust(){
        Score score = new Score(this.score + TEN);
        if(score.isBust()){
            return this;
        }
        return score;
    }

    private boolean isBust(){
        return this.score > BLACKJACK_SCORE;
    }
}
