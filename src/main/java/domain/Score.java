package domain;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

/**
 * 배팅금을 관리하고 수익을 나누는 객체
 */
public class Score {

    public static final int     BLACK_JACK = 21;
    private List<Player>        players;
    private Dealer              dealer;
    private double              bettingMoneySum = 0;

    public Score(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        gatherBettingMoney();
    }

    /**
     * 딜러가 플레이어들의 배팅 금액을 수거하는 함수
     */
    public void gatherBettingMoney() {
        for (Player player : players) {
            bettingMoneySum += player.getBettingMoney();
            player.setProfit(-1 * player.getBettingMoney());
        }
        dealer.setProfit(bettingMoneySum);
    }

    /**
     * 블랙잭이 발생한 경우, 딜러와 플레이어의 점수를 조정한다.
     */
    public void blackJack(List<Player> winners, int dealerScore) {
        if (dealerScore != BLACK_JACK) blackJackWinners(winners);
        if (!winners.isEmpty()) returnBettingMoney(winners);
    }

    /**
     * 승자에게 배팅 금액을 돌려준다.
     */
    public void returnBettingMoney(List<Player> winners) {
        for (Player player : winners) {
            player.setProfit(player.getProfit() + player.getBettingMoney());
            bettingMoneySum -= player.getBettingMoney();
        }
        dealer.setProfit(bettingMoneySum);
        divideBettingMoney(winners);
    }

    /**
     * 블랙잭으로 이긴 승자에게는 배팅 금액의 1.5배를 돌려준다.
     */
    private void blackJackWinners(List<Player> winners) {
        for (Player player : winners) {
            player.setProfit(player.getBettingMoney() * 1.5);
            bettingMoneySum -= player.getBettingMoney() * 1.5;
        }
        dealer.setProfit(bettingMoneySum);
    }

    /**
     * 딜러와 승자가 모인 배팅 금액을 나눈다.
     */
    private void divideBettingMoney(List<Player> winners) {
        if (bettingMoneySum < 0) return;
        double dividedMoney = bettingMoneySum / (winners.size() + 1);
        for (Player player : winners) {
            player.setProfit(player.getProfit() + dividedMoney);
            dealer.setProfit(dealer.getProfit() - dividedMoney);
        }
    }
}