package services;

import controller.BlackJackController;
import domain.game.BlackJack;

import java.util.List;

public class BlackJackUIService {
	public static List<String> printMessageToGetName() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return BlackJackController.getPlayersName();
	}

	public static double printMessageToGetBetting(String player) {
		System.out.println(player + "의 배팅 금액은?");
		return BlackJackController.getBetting();
	}

	public static boolean printInputForMoreCard(String player) {
		System.out.println(player + "는 한장의 카드를 더 받으시겠습니까? y or n");
		return BlackJackController.getMoreCardInput();
	}

	public static void printEmptyInputMessage() {
		System.out.println("플레이어를 입력하여야 합니다");
	}

	public static void printUnderZeroInputMessage() {
		System.out.println("양수를 입력해야 합니다");
	}

	public static void printWrongInputMessage() {
		System.out.println("잘못된 입력입니다");
	}

	public static void printDealerDrawMessage() {
		System.out.println("\n딜러가 카드를 뽑았습니다\n");
	}
}

