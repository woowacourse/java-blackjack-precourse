package domain.card;

public enum Symbol {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private final String name;
    private final Score score;

    Symbol(String name, int score) {
        this.name = name;
        this.score = new Score(score);
    }

    public boolean isAce() {
        return name == ACE.name;
    }

    public Score getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
