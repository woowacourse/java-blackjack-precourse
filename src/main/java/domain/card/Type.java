package domain.card;

public enum Type {
    SPADE("스페이드"),
    DIAMOND("다이아"),
    HEART("하트"),
    CLUB("클로버");

    private String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
