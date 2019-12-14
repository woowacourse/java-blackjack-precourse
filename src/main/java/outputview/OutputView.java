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
			Player player = players.getPlayerAt(i);
			System.out.println(player.getName() + "카드: " + player.getAllCardsInString());
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
	}
	
	private static void showEachResult(Players players, List<WinLoseInfo> info, int index) {
		System.out.println(players.getPlayerAt(index).getName() + "는 블랙잭입니다.");
		if (info.get(index) == WinLoseInfo.DRAW) {
			System.out.println("하지만 딜러도 블랙잭으로 무승부 입니다.");
		}
	}
}
