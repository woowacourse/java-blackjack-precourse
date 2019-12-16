package domain.main;

import java.util.List;

import domain.user.Dealer;
import domain.user.Player;

public class MoneyCalculator {
	private static double dealerIncome = 0;
	
	public static void calculate(List<Player> playerList, Dealer dealer) {
		System.out.println("## 최종 수익 ##");
		for (Player player : playerList) {
			double playerIncome = checkScore(player, dealer, player.bettingMoney());
			System.out.println(player.name() + " : " + playerIncome);
			dealerIncome -= playerIncome;
		}
		System.out.println("딜러 : " + dealerIncome);
	}
	
	public static double checkScore(Player player, Dealer dealer, double playerBettingMoney) {
		double money = blackjackCheck(player, dealer, playerBettingMoney);
		if (money == 0) {
			money = dealerOutCheck(dealer, playerBettingMoney);
		}
		if (money == 0) {
			money = playerOutCheck(player, playerBettingMoney);
		}
		if (money == 0) {
			money = winOrLoseCheck(player, dealer, playerBettingMoney);
		}
		return money;
	}
	
	public static double blackjackCheck(Player player, Dealer dealer, double playerBettingMoney) {
		double money = 0;
		if (player.showScore() == 21 && player.howManyCard() == 2 && (dealer.showScore() != 21 || dealer.howManyCard() != 2)) {
			money = playerBettingMoney*1.5;
		}
		if (player.showScore() == 21 && player.howManyCard() == 2 && dealer.showScore() == 21 && dealer.howManyCard() == 2) {
			money = playerBettingMoney;
		}
		return money;
	}
	
	public static double dealerOutCheck(Dealer dealer, double playerBettingMoney) {
		double money = 0;
		if (dealer.showScore() > 21) {
			money = playerBettingMoney;
		}
		return money;
	}
	
	public static double playerOutCheck(Player player, double playerBettingMoney) {
		double money = 0;
		if (player.showScore() > 21) {
			money = -playerBettingMoney;
		}
		return money;
	}
	
	public static double winOrLoseCheck(Player player, Dealer dealer, double playerBettingMoney) {
		double money = 0;
		if (player.showScore() > dealer.showScore()) {
			money = playerBettingMoney;
		}
		if (player.showScore() < dealer.showScore()) {
			money = -playerBettingMoney;
		}
		return money;
	}

}
