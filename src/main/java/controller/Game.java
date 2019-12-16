package controller;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import view.InputOutputView;

public class Game {
	private static Dealer dealer = new Dealer();
	private static List<Player> players = new ArrayList<>();
	private static List<Card> cards = CardFactory.create();
	private static List<Card> usedCards = new ArrayList<>();
	private static String users;

	public void start() {
		init();
		cardDistribute();
		showCards();
		askMoreCards();
//		InputOutputView.outputShowCardsWithScore();
	}

	private static void init() {
		users = InputOutputView.inputPlayerName();
		initPlayers(users);
	}

	private static void initPlayers(String inputPlayer) {
		for (String name : inputPlayer.split(",")) {
			players.add(new Player(name, InputOutputView.inputBettingMoney(name)));
		}
	}

	private void cardDistribute() {
		giveCard(2, dealer);
		for (Player player : players) {
			giveCard(2, player);
		}
		InputOutputView.outputGiveCards(users);
	}

	private void giveCard(int numberOfCards, Dealer dealer) {
		int randomNumber = (int) ((Math.random() * 1000) % (13 * 4));
		if (numberOfCards != 0 && usedCards.contains(cards.get(randomNumber))) {
			giveCard(numberOfCards, dealer);
		}
		if (numberOfCards != 0 && !usedCards.contains(cards.get(randomNumber))) {
			dealer.addCard(cards.get(randomNumber));
			usedCards.add(cards.get(randomNumber));
			giveCard(numberOfCards - 1, dealer);
		}
	}

	private void giveCard(int numberOfCards, Player player) {
		int randomNumber = (int) ((Math.random() * 1000) % (13 * 4));
		if (numberOfCards != 0 && usedCards.contains(cards.get(randomNumber))) {
			giveCard(numberOfCards, player);
		}
		if (numberOfCards != 0 && !usedCards.contains(cards.get(randomNumber))) {
			player.addCard(cards.get(randomNumber));
			usedCards.add(cards.get(randomNumber));
			giveCard(numberOfCards - 1, player);
		}
	}

	private void showCards() {
		InputOutputView.outputShowCards(dealer);
		for (Player player : players) {
			InputOutputView.outputShowCards(player);
		}
		System.out.println();
	}

	private void askMoreCards() {
		for (Player player : players) {
			checkMoreCards(player);
		}
	}

	private void checkMoreCards(Player player) {
		int moreCardCount;
		do {
			moreCardCount = InputOutputView.inputaskMoreCards(player);
			giveCard(moreCardCount, player);
			InputOutputView.outputShowCards(player);
		} while (checkBust(player) == false && (moreCardCount == 1));
	}

	private boolean checkBust(Player player) {
		if (player.isBust() == true) {
			InputOutputView.outputBust();
			return true;
		}
		return false;
	}

}
