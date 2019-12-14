package com.woowahan.blackjack;

import java.text.ParseException;
import java.util.Scanner;

import com.woowahan.user.User;

public class Console {
	private static boolean printedFinalMessage = false;

	private static String getLine() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	private static void trimStringArray(String[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = array[i].trim();
		}
	}

	public static String[] enterPlayerNames() {
		String[] playerNames;

		System.out.println("쉼표(,)로 구분하여 게임에 참여할 사람들의 이름을 입력해주세요: ");

		playerNames = getLine().split(",");
		trimStringArray(playerNames);

		return playerNames;
	}

	private static int tryParseInt(String string){
		int parsed = 0;
		string = string.trim();
		try {
			parsed = Integer.parseInt(string);
		}
		catch (NumberFormatException e) { }
		return parsed;
	}

	public static int enterBetting(String playerName) {
		int betting = 0;
		String input;
		while (betting <= 0) {
			System.out.print(playerName + "의 베팅 금액: ");

			input = getLine();
			betting = tryParseInt(input);
		}
		return betting;
	}

	public static void printDistribution() {
		System.out.println("무작위 카드 2장을 모두에게 나누었습니다.");
	}

	public static void printCardsOwnedBy(User user, boolean isResult) {
		System.out.print(user.toString());

		if (isResult) {
			System.out.print(" - 결과: " + user.getScore(true));
		}
		System.out.println();
	}

	public static boolean inputDrawOrNot(String playerName) {
		String input = "";

		while (!input.equals("y") && !input.equals("n")) {
			System.out.print(playerName + ", 한장의 카드를 더 받겠습니까? (y/n): ");
			input = getLine();
			input = input.trim();
		}
		return input.equals("y");
	}

	public static void printDealerDraw() {
		System.out.println("딜러가 한장의 카드를 더 받았습니다.");
	}

	public static void printOutcome(String playerName, double money) {
		if (printedFinalMessage == false) {
			System.out.println("## 최종 수익");
			printedFinalMessage = true;
		}

		System.out.println(playerName + ": " + money);
	}
}
