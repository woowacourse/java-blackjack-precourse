package domain.card;

public enum Type {
    SPADE("스페이스"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클럽");

    private String name; // 보다 쉬운 이해를 위한 이름

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
