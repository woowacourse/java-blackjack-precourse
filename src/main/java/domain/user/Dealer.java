package domain.user;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends BaseUser implements User {
    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_MINIMUM_SCORE_TO_STAY = 17;
    private static final String DEALER_SCORE_IS_UNDER_MINIMUM_MESSAGE = "딜러의 점수가 17미만이므로 1장의 카드를 더 받습니다.";

    private Dealer() {}

    public static Dealer create() {
        return new Dealer();
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public boolean isGettingAdditionalCard() {
        return (getScoreOfCards() < DEALER_MINIMUM_SCORE_TO_STAY) && !isBust();
    }

    @Override
    public String getMessageForAdditionalCard() {
        return DEALER_SCORE_IS_UNDER_MINIMUM_MESSAGE;
    }
}
