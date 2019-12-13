package domain.card;

/**
 * Symbol Enum은 카드의 심볼(1~10, J/Q/K)을 나열한다.
 * 이에 대해 점수와 이름(A,J,Q,K에 대한 예외적인 경우 고려)을 부여하고 getter를 제작해둔다.
 */
public enum Symbol {
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
