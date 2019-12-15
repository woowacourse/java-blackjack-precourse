package domain.function;

public enum Outcome {
    BLACKJACK_WIN(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1),
    NOTHING(0);

    private final double rate;

    Outcome(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public boolean isNothing() {
        return this == NOTHING;
    }
}
