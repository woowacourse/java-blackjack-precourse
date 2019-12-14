package domain.card;

public enum Type {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버"),
    KING("킹"),
    QUEEEN("퀸"),
    JACK("잭"),
    ACE("에이스");

    private String koreaType;

    Type(String koreaType) {
        this.koreaType = koreaType;
    }

    public String getKoreaType() {
        return koreaType;
    }
}
