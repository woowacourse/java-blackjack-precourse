package blackjackgame;

import java.util.List;

import domain.user.Player;

public class Calculator {
	private static final boolean BLACKJACK = true;
	private static final boolean NOT_BLACKJACK = false;
	private static final int SUM_BLACKJACK = 21;
	private static final double BLACKJACK_PROFIT = 1.5;
	
	private double dealerMoney = 0;
	
	public double calculateProfit(Player player, List<String> winners, boolean blackJack) {
		double sum = 0;
		if (blackJack == BLACKJACK) {
			sum = blackJackWinner(player, winners);
		} else if (player.sumCardScore() > SUM_BLACKJACK) {
			return -player.getBettingMoney();
		} else if (blackJack == NOT_BLACKJACK) {
			sum = finalWinners(player, winners);
		}
		return sum;
	}
	
	private double blackJackWinner(Player player, List<String> winners) {
		dealerMoney += (player.getBettingMoney() - playerWinningMoney(player, winners));
		if (winners.contains("딜러") && winners.size() > 0) {
			
			return dealerBlackJack(player, winners, dealerMoney);
		} else if (!winners.contains("딜러") && winners.size() > 0) {
			return onlyPlayerBlackJack(player, winners, dealerMoney);
		}
		return 0;
	}
	
	private double dealerBlackJack(Player player, List<String> winners, double dealerMoney) {
		if (player.getName().equals("딜러")) {
			return dealerMoney;
		}
		if (winners.contains(player.getName())) {
			return player.getBettingMoney();
		}
		return -player.getBettingMoney();
	}
	
	private double onlyPlayerBlackJack(Player player, List<String> winners, double dealerMoney) {
		if (player.getName().equals("딜러")) {
			return 0;
		} else if (winners.contains(player.getName())) {
			return player.getBettingMoney() * BLACKJACK_PROFIT;
		}
		return 0;
	}
	
	private double playerWinningMoney(Player player, List<String> winners) {
		if (winners.contains(player.getName())) {
			return player.getBettingMoney();
		}
		return 0;
	}
	
	private double finalWinners(Player player, List<String> winners) {
		dealerMoney += (player.getBettingMoney() - playerWinningMoney(player, winners));
		if (player.getName().equals("딜러")) {
			return 20000;
		} else if (winners.contains(player.getName())) {
			return player.getBettingMoney();
		}
		return -player.getBettingMoney();
	}
	
	public int findWinningScore(int winningScore, int sumCardScore) {
		if (sumCardScore > SUM_BLACKJACK) {
			return winningScore;
		} else if (winningScore > sumCardScore) {
			return winningScore;
		}
		return sumCardScore;
	}
}
