package domain.game;

public enum Constant {
    TARGET(21),
    ALTERNATIVE_ACE(10),
    DEALER_HIT(16);

    private int score;

    Constant(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
