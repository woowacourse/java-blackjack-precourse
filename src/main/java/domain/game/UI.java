package domain.game;

import java.util.List;
import java.util.Scanner;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

public class UI {
	public static void requestUserNameUI() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
	}

	public static void printBettingMoney(String playerName) {
		System.out.println(playerName + "의 배팅 금액은?");
	}

	public static void printDistributeResult(Dealer dealer, List<Player> players) {
		System.out.print("딜러와 ");
		printPlayerName(players.get(0).getName());
		for (int i = 1; i < players.size(); i++) {
			System.out.print(", " + players.get(i).getName());
		}
		System.out.print("에게 2장의 카드를 나누었습니다.\n딜러: ");
		printCard(dealer.getCards().get(0));
		System.out.println();
		for (int i = 0; i < players.size(); i++) {
			printCards(players.get(i));
		}
	}

	public static void printCards(Player player) {
		System.out.print(player.getName() + "카드: ");
		List<Card> cards = player.getCards();
		printCard(cards.get(0));
		for (int i = 1; i < cards.size(); i++) {
			System.out.print(", ");
			printCard(cards.get(i));
		}
		System.out.println();
	}
	
	public static void printCardsAndResult(Player player) {
		System.out.print(player.getName() + "카드: ");
		List<Card> cards = player.getCards();
		printCard(cards.get(0));
		for (int i = 1; i < cards.size(); i++) {
			System.out.print(", ");
			printCard(cards.get(i));
		}
		System.out.println(" - 결과: 	"+player.getScore());
	}

	
	public static void printCards(Dealer dealer) {
		System.out.print("딜러 카드: ");
		List<Card> cards = dealer.getCards();
		printCard(cards.get(0));
		for (int i = 1; i < cards.size(); i++) {
			System.out.print(", ");
			printCard(cards.get(i));
		}
		System.out.println();
	}
	
	public static void printCardsAndResult(Dealer dealer) {
		System.out.print("딜러 카드: ");
		List<Card> cards = dealer.getCards();
		printCard(cards.get(0));
		for (int i = 1; i < cards.size(); i++) {
			System.out.print(", ");
			printCard(cards.get(i));
		}
		System.out.println(" - 결과: 	"+dealer.getScore());
	}


	public static void printCard(Card card) {
		System.out.print(card.getSymbol().getAlphabet() + card.getType().getName());
	}

	public static void printPlayerName(String playerName) {
		System.out.print(playerName);
	}

	public static boolean isOneMoreCard(Player player) {
		if (checkOverflow(player)) {
			return false;
		}
		if (askOneMoreCard(player)) {
			return true;
		}
		return false;
	}

	public static boolean checkOverflow(Player player) {
		if (player.getScore() >= 21) {
			return true;
		}
		return false;
	}

	public static boolean askOneMoreCard(Player player) {
		Scanner scan = new Scanner(System.in);
		System.out.println(player.getName() + "는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
		char input = scan.next().charAt(0);
		if (input == 'y' || input == 'Y') {
			return true;
		}
		if (input == 'n' || input == 'N') {
			return false;
		}
		System.out.println("올바르게 입력해주세요.(예는 y, 아니오는 n)");
		return isOneMoreCard(player);
	}

	public static void announceDealerReceivedOneCard() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}
	
	public static void print
}
