package domain.run;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.sun.javafx.scene.control.skin.FXVK.Type;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;
import domain.user.Dealer;
import domain.user.Player;

public class GameManager {

	final Scanner sc = new Scanner(System.in);
	List<Card> cards = CardFactory.create();
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Card> playerCard = new ArrayList<Card>();
	Integer[] scoreResult;
	int maxScore = 0;
	int winner = 0;
	Random random = new Random();

	Dealer dealer = new Dealer();

	public void printInputName() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		String names = sc.next();
		splitName(names);
	}

	private void splitName(String names) {
		int count = 0;
		for (String name : names.split(",")) {
			System.out.println(name + "의 배팅금액은?");
			players.add(new Player(name, sc.nextDouble()));
			count++;
		}
		scoreResult = new Integer[count + 1];
		drawCard();
		printResult();
	}

	public void printResult() {
		int i = 0;
		for (Player player : players) {
			System.out.print(player.getName() + ": ");
			printPlayerCard(player);
			System.out.print("-결과:");
			System.out.println(printCardResult(player, i));
			i++;
		}
		checkWinnerScore();
		printDealer();
	}

	private void drawCard() {
		for (Player player : players) {
			player.addCard(randomCard());
			player.addCard(randomCard());
		}
		printDealerCard();
		printPlayerName();
		addCardPlayer();
	}

	private void printDealerCard() {
		System.out.println("딜러와 2장의 카드를 나누었습니다");
		dealer.addCard(randomCard());
		dealer.addCard(randomCard());
		System.out.print("딜러: ");
		printPlayerCard(dealer);

	}

	public void printPlayerName() {
		for (Player player : players) {
			System.out.print(player.getName() + "카드: ");
			printPlayerCard(player);
		}
	}

	public void printPlayerCard(Player player) {
		for (int i = 0; i < player.printCard().size(); i++)
			System.out.print(player.printCard().get(i).getSymbol().toString()
					+ player.printCard().get(i).getType().toString() + " ");
		System.out.println();
	}

	private Card randomCard() {
		return checkDuplicationCard(cards.get(random.nextInt(cards.size())));
	}

	private Card checkDuplicationCard(Card card) {

		while (playerCard.contains(card)) {
			card = cards.get(random.nextInt(cards.size()));
		}
		playerCard.add(card);
		return card;
	}

	public void addCardPlayer() {
		for (Player player : players) {
			System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
			choiceAddCard(sc.next(), player);
		}
		checkDealerCardResult();
	}

	private void choiceAddCard(String answer, Player player) {
		if (answer.equals("y") || answer.equals("Y")) {
			player.addCard(randomCard());
		}
		System.out.print(player.getName() + ": ");
		printPlayerCard(player);
		System.out.println();
	}

	private void checkDealerCardResult() {
		if (printCardResult(dealer, players.size()) <= 16) {
			System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다");
			dealer.addCard(randomCard());
		}
		System.out.print("딜러:");
		printPlayerCard(dealer);
		System.out.println("-결과:" + printCardResult(dealer, players.size()));
	}
	private int printCardResult(Player player, int index) {
		int result = 0;
		for (int i = 0; i < player.printCard().size(); i++)
			result += checkCardIntToString(player, i);
		scoreResult[index] = result;
		return result;
	}

	private int checkCardIntToString(Player player, int i) {
		String[] arr = { "Q", "J", "K" };
		if (Arrays.asList(arr).contains((player.printCard().get(i).getSymbol().toString()))) {
			return 10;
		}
		return Integer.parseInt(player.printCard().get(i).getSymbol().toString());
	}

	
}
