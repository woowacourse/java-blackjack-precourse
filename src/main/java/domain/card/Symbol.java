package domain.card;

public enum Symbol {
	/**
	 * 이름 변수를 추가해 소유한 카드를 출력할 때 이용한다.
	 */
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private int score;
    private String name;

    Symbol(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }
    
    public String getName() {
    	return name;
    }
}
