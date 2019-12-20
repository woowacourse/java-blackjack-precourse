package domain.card;

/**
 * 카드의 모양을 의미하는 객체
 */
public enum Type {
    SPADE,
    DIAMOND,
    HEART,
    CLUB;

    public boolean isSame(Type type) {
        return this == type;
    }
}
