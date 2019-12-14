package domain.card;

public enum Type {
    SPADE,
    DIAMOND,
    HEART,
    CLUB;

    public String toKorean() {
        if (this == SPADE) {
            return "스페이드";
        }
        if (this == DIAMOND) {
            return "다이아몬드";
        }
        if (this == HEART) {
            return  "하트";
        }
        return "클로버";
    }
}
