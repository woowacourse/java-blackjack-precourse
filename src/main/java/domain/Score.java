package domain;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class Score {

    public static final int BLACK_JACK = 21;
    private List<Player> players;
    private Dealer dealer;
    private double bettingMoneySum = 0;

    public Score(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        gatherBettingMoney();
    }

    public void gatherBettingMoney() {
        for (Player player : players) {
            bettingMoneySum += player.getBettingMoney();
            player.setProfit(-1 * player.getBettingMoney());
        }
        dealer.setProfit(bettingMoneySum);
    }


    public void blackJack(List<Player> winners, int dealerScore) {
        if (!winners.isEmpty()) returnBettingMoney(winners);
        if (dealerScore != BLACK_JACK) blackJackScore(winners);
    }

    private void returnBettingMoney(List<Player> winners) { //우승자 배팅금액 돌려줌
        for (Player player : winners) {
            player.setProfit(0);
            bettingMoneySum -= player.getBettingMoney();
        }
    }

    private void blackJackScore(List<Player> winners) { //블랙잭으로 우승한 플레이어 1.5배 돌려줌
        for (Player player : winners)
            player.setProfit(player.getBettingMoney() * 1.5);
    }

//    private void divideBettingMoney() //배팅금 우승자+딜러가 나눔
}