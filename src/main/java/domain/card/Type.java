package domain.card;

public enum Type {
    SPADE,
    DIAMOND,
    HEART,
    CLUB;

    public String toKorean() {
        if (this.equals(SPADE)) {
            return "스페이드";
        }
        if (this.equals(DIAMOND)) {
            return "다이아몬드";
        }
        if (this.equals(HEART)) {
            return  "하트";
        }
        return "클로버";
    }
}
