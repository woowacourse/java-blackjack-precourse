package domain.card;

/**
 * Type.java
 * 카드의 모양 가짓수 정보를 지정하는 클래스
 * 우아한테크코스 프리코스 3주차
 * Original code https://github.com/hotheadfactory/java-blackjack-precourse
 * Version: v0.0.1, 2019.12.16 (c) 정회형
 */
public enum Type {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클럽");

    private String alias;

    Type(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

}
