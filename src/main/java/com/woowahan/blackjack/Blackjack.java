package com.woowahan.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.woowahan.card.Card;
import com.woowahan.card.CardFactory;
import com.woowahan.user.Dealer;
import com.woowahan.user.Player;
import com.woowahan.user.User;

public class Blackjack {
	private final int DEALER = 0;
	private final int FIRST_PLAYER = 1;

	List<Card> cards;
	private List<User> users;

	private int getRandomInteger(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	private void initialize() {
		users = new ArrayList<User>();
		users.add(new Dealer());

		cards = CardFactory.create();
	}

	private void invitePlayers() {
		String[] playerNames = Console.enterPlayerNames();

		for (String playerName : playerNames) {
			int bet = Console.enterBetting(playerName);
			Player player = new Player(playerName, bet);

			users.add(player);
		}
	}

	private Card pickRandomCard() {
		int randIdx;
		randIdx = getRandomInteger(0, cards.size() - 1);

		Card pickedCard = cards.get(randIdx);
		cards.remove(randIdx);
		return pickedCard;
	}

	private void drawCard(User user, boolean ifWant) {
		if (ifWant == false) {
			return;
		}

		user.addCard(pickRandomCard());
	}

	private void drawCardTwice(User user) {
		drawCard(user, true);
		drawCard(user, true);
	}

	private void drawCardsEachUser() {
		Console.printDistribution();
		for (User user : users) {
			drawCardTwice(user);
			Console.printCardsOwnedBy(user, false);
		}
	}

	private void askPlayersIfDrawCard() {
		for (int i = FIRST_PLAYER; i < users.size(); i++) {
			Player player = (Player)users.get(i);
			askPlayerIfDrawCard(player);
		}
	}

	private void askPlayerIfDrawCard(Player player) {
		int score = 0;
		boolean ifWant = true;
		while (score <= 20 && ifWant != false) {
			score = player.getScore(false);
			ifWant = Console.inputDrawOrNot(player.getName());
			drawCard(player, ifWant);
			Console.printCardsOwnedBy(player, false);
		}
	}

	private void drawCardIfDealerHasSixteen() {
		Dealer dealer = (Dealer)users.get(DEALER);
		boolean ifDraw = dealer.getScore(true) <= 16;

		drawCard(dealer, ifDraw);

		if (ifDraw) {
			Console.printDealerDraw();
		}
	}

	private void printFinalScores() {
		for (User user : users) {
			Console.printCardsOwnedBy(user, true);
		}
	}

	private void judgeUsersOutcome() {
		printFinalScores();

		double playerOutcomes = 0;
		for (int i = FIRST_PLAYER; i < users.size(); i++) {
			Player player = (Player)users.get(i);
			double playerOutcome = judgePlayerOutcome(player);
			Console.printOutcome(player.getName(), playerOutcome);
			playerOutcomes += playerOutcome;
		}
		Console.printOutcome("딜러", -playerOutcomes);
	}

	private boolean judgeDealerRetire() {
		Dealer dealer = (Dealer)users.get(DEALER);
		return dealer.getScore(true) > 21;
	}

	private boolean judgePlayerDefeat(Player player) {
		Dealer dealer = (Dealer)users.get(DEALER);
		return player.getScore(true) > 21 ||
			player.getScore(true) < dealer.getScore(true);
	}

	private boolean judgePlayerDraw(Player player) {
		Dealer dealer = (Dealer)users.get(DEALER);
		return player.getScore(true) == dealer.getScore(true);
	}

	private double judgeBlackjack(Player player) {
		if (player.getScore(true) == 21) {
			return 1.5;
		}
		return 1;
	}

	private double judgePlayerOutcome(Player player) {
		if (judgeDealerRetire()) {
			return player.getBettingMoney();
		}
		if (judgePlayerDefeat(player)) {
			return -player.getBettingMoney();
		}
		if (judgePlayerDraw(player)) {
			return 0;
		}
		return player.getBettingMoney() * judgeBlackjack(player);
	}

	public void run() {
		initialize();
		invitePlayers();
		drawCardsEachUser();
		askPlayersIfDrawCard();
		drawCardIfDealerHasSixteen();
		judgeUsersOutcome();
	}
}
