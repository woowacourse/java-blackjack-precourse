package domain.view;

import java.util.Scanner;

import domain.validity.ValidityCheck;

public class ViewInput {
	private static Scanner scanner = new Scanner(System.in);
	private static ValidityCheck isValid = new ValidityCheck();
	
	public static String getPlayerNames() {
		try {
			System.out.println("게임에 참여할 사람의 이름을 입력하세.(쉼표 기준으로 분리)");
			return isValid.getName(scanner.nextLine());
			
		} catch (IllegalArgumentException e) {
			System.out.println("잘못된 입력입니다.");
			return getPlayerNames();
		}
	}
	
	public static int getBettingPrice(String name) {
		try {
			System.out.println(name + "의 배팅 금액은?");
			return isValid.getMoney(scanner.nextInt());
		} catch (IllegalArgumentException e) {
			System.out.println("잘못된 입력입니다.");
			return getBettingPrice(name);
		}
	}
}
