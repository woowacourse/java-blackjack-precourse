package domain.manager;

public enum Manual {
    DEALER_POSITION(0),
    DEFAULT(0),
    EMPTY(0),
    CONTINUE(0),
    PLAYER_INIT(1),
    REMOVE_ERROR(1),
    SAFE(10),
    BURST(21),
    DEALER_MIN(16);

    private int value;

    Manual(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
