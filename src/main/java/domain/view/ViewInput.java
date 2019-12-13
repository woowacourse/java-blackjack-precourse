package domain.view;

import java.util.Scanner;
import domain.game.Game;

public class ViewInput {
	private static Scanner scanner = new Scanner(System.in);
	private static Game blackJack = Game.getInstance();
	
	public static String getPlayerNames() {
		try {
			System.out.println("게임에 참여할 사람의 이름을 입력하세.(쉼표 기준으로 분리)");
			return blackJack.nameIsValid(scanner.nextLine());
			
		} catch (IllegalArgumentException e) {
			System.out.println("잘못된 입력입니다.");
			return getPlayerNames();
		}
	}
	
	public static int getBettingPrice(String name) {
		try {
			System.out.println(name + "의 배팅 금액은?");
			return blackJack.moneyIsValid(scanner.nextInt());
		} catch (IllegalArgumentException e) {
			System.out.println("잘못된 입력입니다.");
			return getBettingPrice(name);
		}
	}
}
