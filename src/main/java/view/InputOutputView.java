package view;

import java.util.List;

import org.assertj.core.util.Strings;

import domain.card.Card;
import domain.user.Dealer;
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

	public static void outputGiveCards(String users) {
		System.out.println("딜러와 " + users + "에게 2장의 카드를 나누었습니다.");
	}

	public static void outputShowCards(Dealer dealer) {
		System.out.print("딜러카드 ");
		for (Card card : dealer.getCards()) {
			System.out.print(card.getSymbol() + "-" + card.getType() + " ");
		}
		System.out.println();
	}

	public static void outputShowCards(Player player) {
		System.out.print(player.getName() + "카드 ");
		for (Card card : player.getCards()) {
			System.out.print(card.getSymbol() + "-" + card.getType() + " ");
		}
		System.out.println();
	}

}
