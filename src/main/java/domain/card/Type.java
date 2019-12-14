package domain.card;

public enum Type {
    SPADE("♠"),
    DIAMOND("♦"),
    HEART("♥"),
    CLUB("♣");

    private String suit;

    Type(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
