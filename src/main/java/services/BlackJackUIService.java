package services;

import controller.BlackJackController;

import java.util.List;

public class BlackJackUIService {
	public static List<String> printMessageToGetName() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return BlackJackController.getInputOfPlayersName();
	}

	public static double printMessageToGetBetting(String player) {
		System.out.println(player + "의 배팅 금액은?");
		return BlackJackController.getBetting();
	}

	public static void printEmptyInputMessage() {
		System.out.println("플레이어를 입력하여야 합니다");
	}

	public static void printWrongInputMessage() {
		System.out.println("양수를 입력해야 합니다");
	}

	public static void printInputForMoreCard(String player) {
		System.out.println(player + "는 한장의 카드를 더 받으시겠습니까? y or n");

	}
}

