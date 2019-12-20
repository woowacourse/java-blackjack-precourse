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
        int score = getOptimizedSum().getScore();
        if (!isSurvive()) {
            return -bettingMoney;
        }
        if (score == Score.BLACKJACK_SCORE) {
            return getMoneyWhenHasBlackJack(dealerScore);
        }
        if (score > dealerScore || dealerScore > Score.BLACKJACK_SCORE) {
            return bettingMoney;
        }
        if (score < dealerScore) {
            return -bettingMoney;
        }
        return 0;
    }

    private double getMoneyWhenHasBlackJack(int dealerScore) {
        if (dealerScore == Score.BLACKJACK_SCORE) {
            return 0;
        }
        return bettingMoney * 1.5;
    }
}


























