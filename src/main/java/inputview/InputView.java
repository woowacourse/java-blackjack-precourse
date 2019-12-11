package inputview;

import java.util.Scanner;
import java.util.List;

import domain.user.Players;

public class InputView {
	private static Scanner SCANNER = new Scanner(System.in);
	
	public static List<String> enterPlayerNames() {
		List<String> playerNames;
		try {
			System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
			playerNames = InputParser.parseStringArrToList(SCANNER.nextLine().split(","));
			Players.checkValidNames(playerNames);
			return playerNames;
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return enterPlayerNames();
		}
	}
}
