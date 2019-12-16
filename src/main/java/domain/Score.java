package domain;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

/**
 * 배팅금을 관리하고 수익을 나누는 객체
 */
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
        if (dealerScore != BLACK_JACK) blackJackWinners(winners);
        if (!winners.isEmpty()) returnBettingMoney(winners);
    }

    public void returnBettingMoney(List<Player> winners) { //플레이어와 딜러 모두 21(블랙잭)이거나 플레이어 win
        for (Player player : winners) {
            player.setProfit(player.getProfit() + player.getBettingMoney());
            bettingMoneySum -= player.getBettingMoney();
        }
        dealer.setProfit(bettingMoneySum);
        divideBettingMoney(winners);
    }

    private void blackJackWinners(List<Player> winners) { //블랙잭 플레이어에게 1.5배로 돌려줌
        for (Player player : winners) {
            player.setProfit(player.getBettingMoney() * 1.5);
            bettingMoneySum -= player.getBettingMoney() * 1.5;
        }
        dealer.setProfit(bettingMoneySum);
    }

    private void divideBettingMoney(List<Player> winners) { //배팅금 우승자+딜러가 나눔
        if (bettingMoneySum < 0) return;
        double dividedMoney = bettingMoneySum / (winners.size() + 1);
        for (Player player : winners) {
            player.setProfit(player.getProfit() + dividedMoney);
            dealer.setProfit(dealer.getProfit() - dividedMoney);
        }
    }
}