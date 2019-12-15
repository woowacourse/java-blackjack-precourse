package domain.view;

import java.util.Scanner;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public static String[] inputPlayerNames() {
		String[] playerNames;

		do {
			System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
			String input = scanner.nextLine().trim();
			playerNames = input.split(",");
		} while (isNull(playerNames) || isInvalidInput(playerNames));
		return playerNames;
	}

	private static boolean isNull(String[] players) {
		for (String player : players) {
			if (player.isEmpty()) {
				System.out.println("한 명 이상의 플레이어를 입력해주세요.");
				return true;
			}
		}
		return false;
	}

	private static boolean isInvalidInput(String[] players) {
		if (players.length == 0) {
			System.out.println("입력값 형식이 잘못 되었습니다.");
			return true;
		}
		return false;
	}

	private static boolean isValidNumber(int number) {
		if (number > 0) {
			return true;
		}
		System.out.println("1이상의 양수만 입력해주세요");
		return false;
	}

	public static int getBettingMoney(String player) {
		try {
			System.out.println(player + "의 배팅 금액은?");
			int bettingMoney = Integer.parseInt(scanner.nextLine());
			if (!isValidNumber(bettingMoney)) {
				return getBettingMoney(player);
			}
			return bettingMoney;
		} catch (IllegalArgumentException e) {
			System.out.println("입력값이 올바르지 않습니다.");
			return getBettingMoney(player);
		}
	}
}
