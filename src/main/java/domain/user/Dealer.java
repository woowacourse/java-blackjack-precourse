package domain.user;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends BlackjackUser {
    public static final int MAX_SCORE_NEEDS_MORE_CARD = 16;

    public Dealer() {
        super("딜러");
    }

    // TODO 추가 기능 구현

    public String getFirstCardName() {
        return cards.get(0).getName();
    }

    public boolean doesNeedMoreCard() {
        return getTotalScore() <= MAX_SCORE_NEEDS_MORE_CARD;
    }

}
