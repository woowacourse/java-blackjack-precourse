package domain.main;

import java.util.List;
import java.util.Scanner;
import domain.user.Player;
import domain.card.Symbol;
import domain.user.Dealer;

public class Template {
	
	public static String nameRequest() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리하며 8명까지 참여 가능합니다.)");
		String inputName = scanner.nextLine();
		return inputName;
	}
	
	public static String bettingMoneyRequest(String name) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(name + "의 배팅 금액은?");
		String bettingMoney = scanner.nextLine();
		return bettingMoney;
	}
	
	public static String oneMoreCardRequest(Player player) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(player.name() + "는(은) 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
		String answer = scanner.nextLine();
		return answer;
	}
	
	public static void interimResult(List<Player> players, Dealer dealer) {
		for (Player player : players) {
			System.out.println(player.name()+ " : " + player.showCard());
		}
		System.out.println("딜러 : " + dealer.showOneCard());
		System.out.println();
	}
	
	public static void finalResult(List<Player> players, Dealer dealer) {
		for (Player player : players) {
			System.out.println(player.name()+ " : " + player.showCard() + " - 결과 : " + Integer.toString(player.showScore()));
		}
		System.out.println("딜러 : " + dealer.showCard() + " - 결과 : " + Integer.toString(dealer.showScore()));
		System.out.println();
	}
	
	public static void dealerOneMoreCard() {
		System.out.println("딜러는 16 이하라 한장의 카드를 더 받았습니다.\n");
	}
}
