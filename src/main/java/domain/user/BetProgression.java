package domain.user;

public enum BetProgression {
    Y(true),
    YES(true),
    N(false),
    NO(false);

    private boolean  intention;

    BetProgression(boolean intention) {
        this.intention = intention;
    }

    public boolean getIntention() {
        return intention;
    }

}
