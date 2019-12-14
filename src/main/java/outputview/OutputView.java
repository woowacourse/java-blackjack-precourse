package outputview;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

public class OutputView {
	public static void showInitialCards(Dealer dealer, Players players) {
		System.out.println("딜러와 " + players.toString() + "에게 2장의 카드를 나누었습니다.");
		System.out.println("딜러: " + dealer.getCardsExceptFirstInString());
		for (int i = 0; i < players.getSize(); i++) {
			Player player = players.getPlayerAt(i);
			System.out.println(player.getName() + "카드: " + player.getAllCardsInString());
		}
	}
}
