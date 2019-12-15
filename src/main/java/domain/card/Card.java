package domain.card;

import java.util.Objects;

/**
 * 카드 한장을 의미하는 객체
 */
public class Card {
    private static final int CHANGED_SCORE_OF_ACE = 1;
    private static final int PRESENT_SCORE_OF_ACE = 11;
    private static final String BLANK = " ";

    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    public int getScore() {
        return this.symbol.getScore();
    }

    public boolean changeScoreOfAce() {
        symbol.changeScoreTo(CHANGED_SCORE_OF_ACE);
        return true;
    }

    public boolean isAce() {
        return this.symbol.isScore(PRESENT_SCORE_OF_ACE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return symbol == card.symbol &&
                type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, type);
    }

    @Override
    public String toString() {
        return type.getName() + BLANK + symbol.getName();
    }
}
