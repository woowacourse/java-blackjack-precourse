package domain.card;

public enum Symbol {
    ACE("A", 1, 11),
    TWO("2", 2, 2),
    THREE("3", 3, 3),
    FOUR("4", 4, 4),
    FIVE("5", 5, 5),
    SIX("6", 6, 6),
    SEVEN("7", 7, 7),
    EIGHT("8", 8, 8),
    NINE("9", 9, 9),
    TEN("10", 10, 10),
    JACK("J", 10, 10),
    QUEEN("Q", 10, 10),
    KING("K", 10, 10);

    private final String name;
    private final int score;
    private final int bonusScore;

    Symbol(String name, int score, int bonusScore) {
        this.name = name;
        this.score = score;
        this.bonusScore = bonusScore;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public int getBonusScore() {
        return bonusScore;
    }
}
