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

	private static final int FIRST_INDEX = 0;
    private int score;

    Symbol(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    
    @Override
    public String toString() {
    	if (score < TEN.score | this.equals(TEN)) {
    		return String.valueOf(score);
    	}
    	return "" + this.name().charAt(FIRST_INDEX);
    }
}
