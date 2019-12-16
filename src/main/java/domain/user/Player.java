package domain.user;

import UI.Input.InputController;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends BaseUser implements User {
    private static final String PLAYER_IS_GETTING_ADDITIONAL_CARD_MESSAGE = "님이 1장의 카드를 더 받습니다.";

    private final String name;
    private final double bettingMoney;

    private Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static Player fromNameAndBettingMoney(String name, double bettingMoney) {
        return new Player(name, bettingMoney);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getInitialCards() {
        return getHoldingCards();
    }

    @Override
    public boolean isGettingAdditionalCard() {
        return !isBust() && InputController.askIfGetCard(this.name);
    }

    @Override
    public String getMessageForAdditionalCard() {
        return PLAYER_IS_GETTING_ADDITIONAL_CARD_MESSAGE;
    }
}
