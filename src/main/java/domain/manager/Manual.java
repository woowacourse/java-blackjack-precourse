package domain.manager;

public enum Manual {
    DEALER_POSITION(0),
    DEFAULT(0),
    EMPTY(0),
    CONTINUE(0),
    PLAYER_INIT(1),
    REMOVE_ERROR(1),
    Pair(2),
    SAFE(10),
    DEALER_MIN(16),
    BURST(21),
    BLACKJACK(21);

    private int value;

    Manual(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
