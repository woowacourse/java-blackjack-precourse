package domain.card;

public enum Symbol {
    ACE(11, 'A'),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10, 'J'),
    QUEEN(10, 'Q'),
    KING(10, 'K');

    private int score;
    private char alphabet;

    Symbol(int score) {
        this.score = score;
        this.alphabet = ' ';
    }

    Symbol(int score, char alphabet) {
        this.score = score;
        this.alphabet = alphabet;
    }

    public int getScore() {
        return score;
    }

    public char getAlphabet() {
        return alphabet;
    }
}
