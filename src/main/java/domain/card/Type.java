package domain.card;

public enum Type {
    SPADE,
    DIAMOND,
    HEART,
    CLUB;

    private static final int length = Type.values().length;

    public int getTypeLength() {
        return length;
    }
}