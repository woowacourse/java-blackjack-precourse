
/*
 * ClassName : BlackJackGame
 * 
 * version : 0.1
 * 
 * date : 2019.12.15
 * 
 * author : ParkDooWon
 */

package blackjackgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import view.Input;
import view.Output;

public class BlackJackGame {
	private static final int DEALER_SHOULD_DRAW_MORE_CARD = 16;
	private static final String DEALER_DRAW_ONE_MORE_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

	private List<Player> players = new ArrayList<>();
	private Dealer dealer = new Dealer();
	private Input input = new Input();
	private Output output = new Output();
	private Deck deck = new Deck();
	private Winner winner = new Winner();

	private String[] playerNames;

	public void startGame() {
		getPlayerNames();
		makePlayers();
		deck.makeCardSet();
		drawTwoCards();
		output.showDrawCards(players, dealer);
		findBlackJack();
		drawMoreCards();
		printAllCards();
		findWinner();
	}

	private void getPlayerNames() {
		try {
			playerNames = input.getPlayerNames().split(",");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("asdasdasd");
			getPlayerNames();
		}
	}

	private void makePlayers() {
		for (String name : playerNames) {
			players.add(new Player(name, getBettingMoney(name)));
		}
	}

	private double getBettingMoney(String name) {
		try {
			return input.getBettingMoney(name);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			getBettingMoney(name);
		}
		return 0;
	}

	private void drawTwoCards() {
		for (int i = 0; i < 2; i++) {
			players.stream().forEach(player -> player.addCard(deck.drawCard()));
			dealer.addCard(deck.drawCard());
		}
	}

	private void findBlackJack() {
		if (winner.findBlackJack(players, dealer).size() > 0) {
			winner.printBlackJackWinners(winner.findBlackJack(players, dealer), players, dealer);
			System.exit(0);
		}
	}

	private void drawMoreCards() {
		for (Player player : players) {
			playerDrawMoreCard(player);
			player.getSymbolAndType();
			System.out.println();
		}
		while (dealer.sumCardScore() <= DEALER_SHOULD_DRAW_MORE_CARD) {
			dealer.addCard(deck.drawCard());
			System.out.println(DEALER_DRAW_ONE_MORE_CARD + '\n');
		}
	}

	private void playerDrawMoreCard(Player player) {
		try {
			input.giveOneMoreCard(player, deck);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			playerDrawMoreCard(player);
		} catch (Exception e) {
			player.addCard(deck.drawCard());
			player.getSymbolAndType();
			overTwentyOne(player);
		}
	}

	private void overTwentyOne(Player player) {
		if (player.sumCardScore() <= 21) {
			playerDrawMoreCard(player);
		} else if (player.sumCardScore() > 21) {
			player.printBustPlayer();
		}
	}

	private void printAllCards() {
		dealer.printAllCards(dealer);
		for (Player player : players) {
			player.printAllCards();
		}
	}

	private void findWinner() {
		winner.printFinalWinners(winner.findFinalWinners(players, dealer), players, dealer);
	}

}
