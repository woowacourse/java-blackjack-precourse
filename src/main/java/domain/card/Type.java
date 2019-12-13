package domain.card;

/**
 * Type Enum은 카드의 타입(스페이드/다이아몬드/하트/클럽)을 나열한다.
 * 이에 대한 type을 문자열로 가지고 있어, getter를 통해 이를 구할 수 있다.
 */
public enum Type {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클럽");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
