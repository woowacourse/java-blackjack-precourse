package domain.user;

import exception.Validator;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User{
    private static final String YES = "y";

    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        checkCorrectPlayer(name, bettingMoney);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    private void checkCorrectPlayer(String name, double bettingMoney) {
        Validator.checkPlayerName(name);
        Validator.validateBettingMoney(bettingMoney);
    }
}
