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
	private static final String BURST_MESSAGE = "BURST! 21 초과\n";
	private static final String FINAL_SCORE = "\n## 최종 점수";
	private static final String BLACKJACK = "가 블랙잭 입니다!!";
	private static final String DEALER_HIT = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
	private static final String DEALER_STAND = "딜러는 17이상이라 카드를 받지 않습니다.\n";

	public static void printPlayerCards(Player player) {
		String result = player.getCardsWithName();
		System.out.println(result);
	}

	public static void printDealerInitialCards(Dealer dealer) {
		String result = Dealer.getNAME() + COLON + dealer.getCards().split(COMMA)[FIRST_CARD];
		System.out.println(result);
	}

	public static void printDealerCards(Dealer dealer) {
		String result = dealer.getCardsWithName();
		System.out.println(result);
	}

	public static void printFinalScore(Dealer dealer, List<Player> playerList) {
		printDealerScore(dealer);
		for (Player player : playerList) {
			printPlayerScore(player);
		}
	}

	private static void printDealerScore(Dealer dealer) {
		StringBuilder sb = new StringBuilder();
		sb.append(dealer.getCardsWithName());
		printScore(sb, dealer.bust(), dealer.sumCardScore());
	}

	private static void printPlayerScore(Player player) {
		StringBuilder sb = new StringBuilder();
		sb.append(player.getCardsWithName());
		printScore(sb, player.bust(), player.sumCardScore());
	}

	private static void printScore(StringBuilder sb, boolean bust, int sumCardScore) {
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

	public static void printCardBustMessage() {
		System.out.println(BURST_MESSAGE);
	}

	public static void printBlackjackMessage(String name) {
		System.out.println(name + BLACKJACK);
	}

	public static void printDealerHit() {
		System.out.println(DEALER_HIT);
	}

	public static void printDealerStand() {
		System.out.println(DEALER_STAND);
	}

}
