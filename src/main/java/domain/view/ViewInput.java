package domain.view;

import java.util.Scanner;

import domain.game.Game;
import domain.user.Player;
import domain.validity.ValidityCheck;

public class ViewInput {
	private static Scanner scanner = new Scanner(System.in);
	private static ValidityCheck validity = new ValidityCheck();
	private static Game blackJack = Game.getInstance();
	
	public static String getPlayerNames() {
		try {
			System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
			return validity.nameIsValid(scanner.nextLine());
		} catch (IllegalArgumentException e) {
			System.out.println("잘못된 입력입니다.");
			return getPlayerNames();
		}
	}
	
	public static String getPlayerName(String player) {
		try {
			return validity.nameIsValid(player);
		} catch (IllegalArgumentException e) {
			System.out.println("잘못된 이름이 있습니다.");
			return "";
		}
	}
	
	public static int getBettingPrice(String name) {
		try {
			System.out.println(name + "의 배팅 금액은?");
			return validity.moneyIsValid(scanner.nextLine());
		} catch (IllegalArgumentException e) {
			System.out.println("잘못된 입력입니다.");
			return getBettingPrice(name);
		}
	}
	
	public static String askGetCard(Player player) {
		String answer;
		
		System.out.println(player.getName() + "은(는) 한장의 카드를 더 받겠습니까?(예는 y,아니오는 n)");
		answer = scanner.nextLine();
		
		if (answer.equals("y")) {
			player.addCard(blackJack.selectedCard());
		}
		return answer;
	}
}