package inputview;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.List;

import domain.user.Player;
import domain.user.Players;

public class InputView {
	private static Scanner SCANNER = new Scanner(System.in);
	
	public static List<String> enterPlayerNames() {
		List<String> playerNames;
		try {
			System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
			playerNames = InputParser.parseStringArrToList(SCANNER.nextLine().split(","));
			Players.checkValidNames(playerNames);
			System.out.println();
			return playerNames;
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return enterPlayerNames();
		}
	}
	
	public static List<Integer> enterAllBettingMoney(List<String> playerNames) {
		return playerNames.stream()
				.map(InputView::enterBettingMoney)
				.collect(Collectors.toList());
	}
	
	private static int enterBettingMoney(String name) {
		int money;
		try {
			System.out.println(name + "의 배팅 금액은?");
			money = InputParser.parseStringToInt(SCANNER.nextLine());
			Player.checkValidBettingMoney(money);
			System.out.println();
			return money;
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return enterBettingMoney(name);
		}
	}
}
