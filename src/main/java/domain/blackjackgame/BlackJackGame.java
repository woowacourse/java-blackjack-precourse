package domain.blackjackgame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.assertj.core.util.CheckReturnValue;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class BlackJackGame {
	private static final int TWO = 2;
	private static final int DEALER_CARD_STANDARD = 16;
	private static final int MAX_SCORE = 21;

	private final Scanner scanner;
	private final List<Player> players;
	private final List<Card> cards;
	private final Set<Integer> checkSameCard;
	private final Dealer dealer;

	public BlackJackGame() {
		scanner = new Scanner(System.in);
		players = new ArrayList<>();
		dealer = new Dealer();
		cards = CardFactory.create();
		checkSameCard = new HashSet<>();
	}

	public void init() {
		while (!inputPlayer()) {
		}
		startGame();
		printCards();
		playGame();
	}

	private boolean inputPlayer() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		String[] playerNames = scanner.nextLine().split(",");
		if (playerNames.length == 0) {
			return false;
		}
		for (String playerName : playerNames) {
			while (!inputBettingMoney(playerName)) {
			}
		}
		return true;
	}

	private boolean inputBettingMoney(String playerName) {
		System.out.println(playerName + "의 배팅 금액은?");
		try {
			double bettingMoney = Double.parseDouble(scanner.nextLine());
			Player player = new Player(playerName, bettingMoney);
			players.add(player);
			return true;
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다. 다시 입력해 주세요");
			return false;
		}
	}

	private void startGame() {
		giveFirstTwoCards(dealer);
		for (Player player : players) {
			giveFirstTwoCards(player);
		}
		System.out.print("딜러와 ");
		for (Player player : players) {
			System.out.print(player.getName()+" ");
		}
		System.out.println("에게 2장의 카드를 나누어주었습니다.");
	}

	private void giveFirstTwoCards(User user) {
		for (int i = 0; i < TWO; i++) {
			giveCard(user);
		}
	}

	private void giveCard(User user) {
		Card randomCard = randomizeCard();
		user.addCard(randomCard);
	}

	private Card randomizeCard() {
		Random generator = new Random();
		int randomIndex = 0;
		while (checkSameCard.contains(randomIndex = generator.nextInt(cards.size()))) {
		}
		Card randomCard = cards.get(randomIndex);
		checkSameCard.add(randomIndex);
		return randomCard;
	}

	private void playGame() {
		for(Player player : players) {
			getMoreCards(player);
		}
	}

	private void getMoreCards(Player player) {
		while(!isMaxScore(player)) {
			System.out.println(player.getName()+"는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
			String input = scanner.nextLine();
			if(input.equals("y")) {
				giveCard(player);
				printCards();
				continue;
			}
			if(input.equals("n")) {
				return;
			}
			System.out.println("잘못된 입력을 하셨습니다. 다시 입력해주세요.");
		}
	}
	
	private boolean isMaxScore(User user) {
		if(user.getScore() >= MAX_SCORE) {
			return true;
		}
		return false;
	}
	
	private void printCards() {
		System.out.println("====================================");
		System.out.print("딜러의 카드: ");
		dealer.printCards();
		for (Player player : players) {
			System.out.print(player.getName() + "의 카드: ");
			player.printCards();
		}
		System.out.println("====================================");
	}
}
