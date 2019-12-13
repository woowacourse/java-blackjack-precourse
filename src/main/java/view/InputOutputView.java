package view;

import java.util.List;
import java.util.Scanner;

import domain.user.Player;
import util.ExceptionHandler;

public class InputOutputView {

	public static String inputPlayerName() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return ExceptionHandler.inputPlayerHandler();
	}

	public static double inputBettingMoney(String name) {
		System.out.println(name + "의 배팅 금액은?");
		return ExceptionHandler.inputBettingMoneyHandler();
	}
}
