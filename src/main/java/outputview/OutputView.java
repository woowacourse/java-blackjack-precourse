package outputview;

import java.util.List;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import application.WinLoseInfo;

public class OutputView {
	public static void showInitialCards(Dealer dealer, Players players) {
		System.out.println("딜러와 " + players.toString() + "에게 2장의 카드를 나누었습니다.");
		System.out.println("딜러: " + dealer.getCardsExceptFirstInString());
		for (int i = 0; i < players.getSize(); i++) {
			showPlayerCards(players.getPlayerAt(i));
		}
		showBlankLine();
	}
	
	public static void showInitialResult(Player player, WinLoseInfo info) {
		System.out.println(player.getName() + "는 블랙잭입니다.");
		if (info == WinLoseInfo.DRAW) {
			System.out.println("하지만 딜러도 블랙잭으로 무승부 입니다.");
		}
		showBlankLine();
	}
	
	public static void showPlayerCards(Player player) {
		System.out.println(player.getName() + "카드: " + player.getAllCardsInString());
	}
	
	public static void showDealerDrawsCard() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}
	
	public static void showDealerLose() {
		System.out.println("딜러의 카드 합이 21을 초과해 파산했습니다. 남아있는 사람은 모두 승리합니다.");
	}
	
	public static void showAllFinalResults(Dealer dealer, Players players, List<WinLoseInfo> info) {
		showDealerCardsWithScore(dealer);
		for (int i = 0; i < players.getSize(); i++) {
			showPlayerCardsWithScore(players.getPlayerAt(i), info.get(i));
		}
		showBlankLine();
	}
	
	private static void showDealerCardsWithScore(Dealer dealer) {
		System.out.println("딜러 카드: " 
				+ dealer.getAllCardsInString()
				+ " - 결과: "
				+ dealer.calculateScore());
	}
	
	private static void showPlayerCardsWithScore(Player player, WinLoseInfo info) {
		System.out.println(player.getName() 
				+ " 카드: " 
				+ player.getAllCardsInString()
				+ " - 결과: "
				+ player.calculateScore()
				+ ", " + info.toString());
	}
	
	public static void showFinalProfit(Players players, List<Integer> profits) {
		StringBuilder message = new StringBuilder();
		message.append("##최종 수익\n");
		message.append("딜러: " + (-profits.stream().reduce(0, Integer::sum)) + "\n");
		for (int i = 0; i < profits.size(); i++) {
			message.append(players.getPlayerAt(i).getName() + ": " + profits.get(i) + "\n");
		}
		System.out.println(message);
	}
	
	public static void showBlankLine() {
		System.out.println();
	}
}
