package domain.user;

import game.GameConstants;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Entry {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    @Override
    public String toString() {
        return name + "의 카드 : " + this.getAllCardName();
    }

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

    public boolean isWinner(int dealerScore) {
        return this.getScore() > dealerScore || dealerScore > GameConstants.BLACKJACK;
    }

    public boolean isLoser(int dealerScore) {
        return this.getScore() < dealerScore || this.getScore() > GameConstants.BLACKJACK;
    }

    public boolean isDrawn(int dealerScore) {
        return this.getScore() == dealerScore;
    }

    public boolean isBlackjack() {
        return this.getScore() == GameConstants.BLACKJACK;
    }
}
