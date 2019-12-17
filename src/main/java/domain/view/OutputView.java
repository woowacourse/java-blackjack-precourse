package domain.view;

import java.util.List;

import domain.casino.Money;
import domain.user.Dealer;
import domain.user.Player;

public class OutputView {
	private static final String COMMA = ",";
	private static final int FIRST_CARD = 0;
	private static final String RESULT = " - 결과: ";
	private static final String COLON = ": ";
	private static final String BURST = "BURST";
	private static final String FINAL_SCORE = "\n## 최종 점수";

	public static void printPlayerCards(Player player) {
		String result = player.getPlayerCards();
		System.out.println(result);
	}

	public static void printDealerInitialCards(Dealer dealer) {
		String result = dealer.getDealerCards().split(COMMA)[FIRST_CARD];
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
		sb.append(RESULT);
		if (bust) {
			sb.append(BURST);
			System.out.println(sb.toString());
			return;
		}
		sb.append(sumCardScore);
		System.out.println(sb.toString());
	}

	public static void printEarning(String playerName, double earning) {
		System.out.println(playerName + COLON + earning);
	}

	public static void printFinalEarning(Dealer dealer, List<Player> playerList) {
		System.out.println(FINAL_SCORE);
		Money money = new Money(playerList);
		money.calculatePlayersEarning(dealer, playerList);
		money.calculateDealerEarning(playerList);

		money.printPlayersEarning(playerList);
		money.printDealerEarning();
	}

}
