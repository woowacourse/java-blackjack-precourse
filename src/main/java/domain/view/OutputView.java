package domain.view;

import java.util.List;

import domain.user.Dealer;
import domain.user.Player;

public class OutputView {

	public static void printPlayerCards(Player player) {
		String result = player.getPlayerCards();
		System.out.println(result);
	}

	public static void printDealerInitialCards(Dealer dealer) {
		String result = dealer.getDealerCards().split(",")[0];
		System.out.println(result);
	}

	public static void printDealerCards(Dealer dealer) {
		String result = dealer.getDealerCards();
		System.out.println(result);
	}

	public static boolean isLessThanSeventeen(int dealerScore) {
		if (dealerScore <= 16) {
			System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
			return true;
		}
		System.out.println("딜러는 17이상이라 카드를 받지 않습니다.");
		return false;
	}

	public static void printFinalScore(Dealer dealer, List<Player> playerList) {
		printDealerScore(dealer);
		for (Player player : playerList) {
			printPlayerScore(player);
		}
		System.out.println();
	}

	private static void printDealerScore(Dealer dealer) {
		StringBuffer sb = new StringBuffer();
		sb.append(dealer.getDealerCards());
		printScore(sb, dealer.bust(), dealer.sumCardScore());
	}

	private static void printPlayerScore(Player player) {
		StringBuffer sb = new StringBuffer();
		sb.append(player.getPlayerCards());
		printScore(sb, player.bust(), player.sumCardScore());
	}

	private static void printScore(StringBuffer sb, boolean bust, int sumCardScore) {
		sb.append(" - 결과: ");
		if (bust) {
			sb.append("BURST");
			System.out.println(sb.toString());
			return;
		}
		sb.append(sumCardScore);
		System.out.println(sb.toString());
	}

	public static void printFinalEarning(Dealer dealer, List<Player> playerList) {
		int dealerMoney = 0;
		int dealerScore = dealer.sumCardScore();

		System.out.println("## 최종 수익");
		for (Player player : playerList) {
			if (player.isBlackJack()) {
				dealerMoney -= player.getBettingMoney() * 1.5;
				System.out.println(player.getName() + ": " + player.getBettingMoney() * 1.5);
			} else if (player.bust()) {
				dealerMoney += player.getBettingMoney();
				System.out.println(player.getName() + ": -" + player.getBettingMoney());
			} else if (dealer.bust()) {
				System.out.println(player.getName() + ": " + player.getBettingMoney());
				dealerMoney -= player.getBettingMoney();
			} else if (player.sumCardScore() <= 21 && player.sumCardScore() > dealerScore) {
				System.out.println(player.getName() + ": " + player.getBettingMoney());
				dealerMoney -= player.getBettingMoney();
			} else if (player.sumCardScore() == dealerScore) {
				System.out.println(player.getName() + ": " + 0);
			} else {
				dealerMoney += player.getBettingMoney();
				System.out.println(player.getName() + ": -" + player.getBettingMoney());
			}
		}
		System.out.println("딜러: " + dealerMoney);
	}

}
