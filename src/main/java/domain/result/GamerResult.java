package domain.result;

import domain.user.Gamer;

import static domain.result.ResultGamersScore.BLACK_JACK_NUMBER;

public class GamerResult {
    private double resultMoney;
    private int blackJackScore;

    public GamerResult(Gamer gamer) {
        this.blackJackScore = BLACK_JACK_NUMBER - gamer.getScoreOfGamer();
    }

    public boolean isBlackJack() {
        return blackJackScore == 0;
    }

    public double getResultMoney() {
        return resultMoney;
    }

    public void setResultMoney(double money) {
        this.resultMoney = money;
    }

    public int getBlackJackScore() {
        return blackJackScore;
    }

    public String getName() {
        return "";
    }

}
