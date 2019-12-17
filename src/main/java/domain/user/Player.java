package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Member {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    // TODO 추가 기능 구현
    public String getName() {
        return name;
    }

    public String bettingInfo() {
        return name + ": " + Double.toString(bettingMoney);
    }

    public String cardInfo() {
        return name +
                ":\n" +
                toStringCards();
    }

    public double getMoney(int dealerScore) {
        if (!isSurvive()) {
            return -bettingMoney;
        }
        if (getOptimizedSum() == 21) {
            return getMoneyWhenHasBlackJack(dealerScore);
        }
        if (getOptimizedSum() > dealerScore || dealerScore > 21) {
            return bettingMoney;
        }
        if (getOptimizedSum() < dealerScore) {
            return -bettingMoney;
        }
        return 0;
    }

    private double getMoneyWhenHasBlackJack(int dealerScore) {
        if (dealerScore == 21) {
            return 0;
        }
        return bettingMoney * 1.5;
    }
}


























