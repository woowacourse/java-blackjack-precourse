package domain.card;

public enum Type {
    SPADE,
    DIAMOND,
    HEART,
    CLUB;

    public String toString() {
        String returnValue = new String();
        if (this == Type.SPADE) {
            returnValue = "스페이드";
        }
        if (this == Type.DIAMOND) {
            returnValue = "다이아몬드";
        }
        if (this == Type.HEART) {
            returnValue = "하트";
        }
        if (this == Type.CLUB) {
            returnValue = "클로버";
        }
        return returnValue;
    }
}
