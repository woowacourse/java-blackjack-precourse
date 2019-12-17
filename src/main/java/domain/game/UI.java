package domain.game;

import java.util.List;

import domain.user.Dealer;
import domain.user.Player;

public class UI {
	public static void requestUserNameUI() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
	}
	public static void printBettingMoney(String playerName) {
		System.out.println(playerName + "의 배팅 금액은?");
	}
	public static void printDistributeResult(Dealer dealer, List<Player> players) {
		System.out.print("딜러와 ");
		printPlayerName(players.get(0).getName());
		for(int i = 1; i<players.size(); i++) {
			System.out.print(", " + players.get(i).getName());
		}
		System.out.println("에게 2장의 카드를 나누었습니다.\n딜러: " + dealer.getCards().get(0));
		for(int i = 0; i<players.size(); i++) {
			printCards(players.get(i));
		}
	}
	
	public static void printCards(Player player) {
		System.out.print(player.getName()+"카드: ");
		for(int i = 0; i<)
	}
	public static void printPlayerName(String playerName) {
		System.out.print(playerName);
	}
}
