package domain.card;

public enum Type {
    SPADE("♠"),
    DIAMOND("♦"),
    HEART("♥"),
    CLUB("♣");

    private String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
