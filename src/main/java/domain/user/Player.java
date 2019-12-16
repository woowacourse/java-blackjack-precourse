package domain.user;

import domain.card.Symbol;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Gamer {
    private final String name;
    private double bettingMoney;

    public String getName() {
        return name;
    }

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    // TODO 추가 기능 구현

    public void multiplyProfit(double multiple) {
        bettingMoney *= multiple;
    }

    public double getProfit() {
        return bettingMoney;
    }

    public boolean isWinGame(Dealer dealer) {
        if (dealer.isBurst() && !isBurst())
            return true;
        if (dealer.sumOfCard() < sumOfCard() && !dealer.isBurst() && !isBurst())
            return true;
        return false;
    }

    public boolean isDrawGame(Dealer dealer) {
        if (dealer.sumOfCard() == sumOfCard()
                && !dealer.isBurst() && !isBurst())
            return true;
        return false;
    }
}
