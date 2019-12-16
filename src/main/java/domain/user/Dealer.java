package domain.user;

import java.util.stream.Collectors;

/**
 * Dealer.java
 * 게임 딜러를 의미하는 객체.
 * 우아한테크코스 프리코스 3주차
 * Original code https://github.com/hotheadfactory/java-blackjack-precourse
 * Version: v0.0.1, 2019.12.16 (c) 정회형
 */
public class Dealer extends Person {
    private static final int REDRAW_LIMIT = 16;

    public Dealer() {
    }

    public String getCardString() {
        return ("딜러: " + listCard(getKoreanName().stream().limit(1).collect(Collectors.toList())));
    }

    public String getFinalCardString() {
        return ("딜러 카드: " + listCard(getKoreanName()) + " - 결과: " + this.calculateScore() + super.concatBust());
    }

    public boolean isBelowRedraw() {
        return (calculateScore() <= REDRAW_LIMIT);
    }

}
