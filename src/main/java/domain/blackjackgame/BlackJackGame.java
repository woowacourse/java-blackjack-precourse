package domain.blackjackgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class BlackJackGame {
	private static final int TWO = 2;

	private final Scanner scanner;
	private final List<Player> players;
	private final List<Card> cards;
	private final Dealer dealer;
	private String[] playerNames;

	public BlackJackGame() {
		scanner = new Scanner(System.in);
		players = new ArrayList<>();
		dealer = new Dealer();
		cards = CardFactory.create();
	}

	public void init() {
		while (!inputPlayer()) {
		}
		startGame();
		playGame();
	}

	private boolean inputPlayer() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		playerNames = scanner.nextLine().split(",");
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
		for (Player player : players) {
			giveFirstTwoCards(player);
		}
		giveFirstTwoCards(dealer);
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
		int randomIndex = generator.nextInt(cards.size());
		Card randomCard = cards.get(randomIndex);
		cards.remove(randomIndex);
		return randomCard;
	}
	
	private void playGame() {
		
	}
}
