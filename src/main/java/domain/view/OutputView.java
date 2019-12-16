package domain.view;

import java.util.List;

import domain.casino.Money;
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

	public static void printFinalScore(Dealer dealer, List<Player> playerList) {
		printDealerScore(dealer);
		for (Player player : playerList) {
			printPlayerScore(player);
		}
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

	public static void printPlayerEarning(String playerName, double earning) {
		System.out.println(playerName + ": " + earning);
	}

	public static void printFinalEarning(Dealer dealer, List<Player> playerList) {
		System.out.println("\n## 최종 점수");
		Money money = new Money(playerList);
		money.calculatePlayersEarning(dealer, playerList);
		money.calculateDealerEarning(playerList);
	}

}
