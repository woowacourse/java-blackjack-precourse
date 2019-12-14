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
		System.out.println();
	}
	
	public static void showInitialResult(Players players, List<WinLoseInfo> info, List<Integer> blackjackIndex) {
		if (blackjackIndex.isEmpty()) {
			System.out.println("처음에 아무도 블랙잭들 만들지 못했습니다");
		}
		for (int i = 0; i < blackjackIndex.size(); i++) {
			showEachResult(players, info, blackjackIndex.get(i));
		}
		System.out.println();
	}
	
	private static void showEachResult(Players players, List<WinLoseInfo> info, int index) {
		System.out.println(players.getPlayerAt(index).getName() + "는 블랙잭입니다.");
		if (info.get(index) == WinLoseInfo.DRAW) {
			System.out.println("하지만 딜러도 블랙잭으로 무승부 입니다.");
		}
	}
	
	public static void showPlayerCards(Player player) {
		System.out.println(player.getName() + "카드: " + player.getAllCardsInString());
	}
	
	public static void showDealerDrawsCard() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}
	
	public static void showAllFinalResults(Dealer dealer, Players players, List<WinLoseInfo> info) {
		System.out.println("딜러 카드: " + dealer.getAllCardsInString()
					+ " - 결과: "
					+ dealer.calculateScore());
		for (int i = 0; i < players.getSize(); i++) {
			showPlayerCardsWithScore(players.getPlayerAt(i), info.get(i));
		}
		System.out.println();
	}
	
	private static void showPlayerCardsWithScore(Player player, WinLoseInfo info) {
		System.out.println(player.getName() + " 카드: " 
				+ player.getAllCardsInString()
				+ " - 결과: "
				+ player.calculateScore()
				+ ", " + info.toString());
	}
	
	public static void showBlankLine() {
		System.out.println();
	}
}
