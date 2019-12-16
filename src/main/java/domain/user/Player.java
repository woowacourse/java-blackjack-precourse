package domain.user;

import domain.card.Symbol;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Gamer{
    private final String name;
    private double bettingMoney;

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void multiplyProfit(double multiple) {
        bettingMoney *= multiple;
    }

    // TODO 추가 기능 구현

}
