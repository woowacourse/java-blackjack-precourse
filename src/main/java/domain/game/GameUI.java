package domain.game;

import java.util.ArrayList;

import domain.user.Person;
import domain.user.Player;

public class GameUI {
	private final static int END_WORD = 2;

	public GameUI() {
	}

	public static void requestUserNameInterface() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표를 기준으로 분리)");
	}

	public static void requestUserBettingMoneyInterface(String name) {
		System.out.println(name + "의 배팅 금액은?");
	}

	public static void requestUserBettingMoneyFixInterface() {
		System.out.println("제대로 된 값을 입력하세요(양수)");
	}

	public static void requestUserNameFixInterface() {
		System.out.println("5글자 이하의 이름을 입력하세요");
	}

	public static void parsingName(ArrayList<Player> userNameList) {
		StringBuilder parseName;
		parseName = new StringBuilder("딜러와 ");
		for (int i = 0; i < userNameList.size(); i++) {
			parseName.append(userNameList.get(i).getName() + ", ");
		}
		parseName.delete(parseName.length() - END_WORD, parseName.length());
		parseName.append("에게 2장의 카드를 나누어주었습니다.");
		System.out.println(parseName);
	}

	public static void printPersonCard(StringBuilder cardList) {
		System.out.println(cardList);
	}

	public static void printRequestAddCard(String name){
		System.out.println(name + "은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
	}
}
