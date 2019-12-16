package domain.card;

/**
 * Symbol.java
 * 카드의 문자 가짓수 정보를 지정하는 클래스
 * 우아한테크코스 프리코스 3주차
 * Original code https://github.com/hotheadfactory/java-blackjack-precourse
 * Version: v0.0.1, 2019.12.16 (c) 정회형
 */
public enum Symbol {
    ACE(1,"A"),
    TWO(2,"2"),
    THREE(3,"3"),
    FOUR(4,"4"),
    FIVE(5,"5"),
    SIX(6,"6"),
    SEVEN(7,"7"),
    EIGHT(8,"8"),
    NINE(9,"9"),
    TEN(10,"10"),
    JACK(10,"잭"),
    QUEEN(10,"퀸"),
    KING(10,"킹");

    private int score;
    private String alias;

    Symbol(int score, String alias) {
        this.score = score;
        this.alias = alias;
    }

    public int getScore() {
        return score;
    }

    public String getAlias() {
        return alias;
    }
}
