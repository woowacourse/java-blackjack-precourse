package domain.card;

public enum Symbol {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private int score;

    Symbol(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        if (this.getScore() == ACE.getScore()) {
            return "A";
        }
        if ((this == Symbol.JACK) || (this == Symbol.QUEEN) || (this == Symbol.KING)) {
            return this.name().substring(0, 1);
        }

        return Integer.toString(this.getScore());
    }
}
