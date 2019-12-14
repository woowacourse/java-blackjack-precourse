package domain.user;

import java.util.stream.Collectors;

/**
 * 게임 딜러를 의미하는 객체
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
