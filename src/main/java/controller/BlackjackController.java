package controller;

import java.util.ArrayList;
import java.util.List;

import domain.card.CardDeck;
import domain.rule.Rule;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import view.InputView;
import view.OutputView;

public class BlackjackController {
	private static final String HIT = "y";

	private final CardDeck cardDeck = new CardDeck();
	private final Dealer dealer = new Dealer();
	private final List<Player> players = new ArrayList<>();

	public BlackjackController() {
		initPlayers();
	}

	private void initPlayers() {
		String[] names = InputView.getPlayerNames();
		for (String name : names) {
			int bettingMoney = InputView.getBettingMoney(name);
			players.add(new Player(name, bettingMoney));
		}
	}

	private void giveTwoCard() {
		dealer.giveCard(cardDeck, dealer);
		dealer.giveCard(cardDeck, dealer);
		for (Player player : players) {
			dealer.giveCard(cardDeck, player);
			dealer.giveCard(cardDeck, player);
		}
	}

	private void printFaceUpCards() {
		OutputView.printCards(dealer.getName(), dealer.getFaceUpCards());
		for (Player player : players) {
			OutputView.printCards(player.getName(), player.getCards());
		}
	}

	private void printScore(User user) {
		OutputView.printScore(user.getName(), user.getCards(), user.getSumOfCards());
	}

	private void printAllUsersScore() {
		printScore(dealer);
		players.forEach(this::printScore);
	}

	private void onHit(boolean isHit, Player player) {
		if (isHit) {
			dealer.giveCard(cardDeck, player);
			OutputView.printCards(player.getName(), player.getCards());
		}
	}

	private void getMoreCard(Player player) {
		boolean isHit = true;
		while (Rule.canGetMoreCards(player) && isHit) {
			isHit = InputView.getMoreCard(player.getName()).equals(HIT);
			onHit(isHit, player);
		}
	}

	private void getMoreCards() {
		players.forEach(this::getMoreCard);
		if (Rule.shouldAddCard(dealer)) {
			dealer.giveCard(cardDeck, dealer);
			OutputView.printDealerAddCard(dealer.getName());
		}
	}

	public void run() {
		giveTwoCard();
		printFaceUpCards();
		getMoreCards();
		printAllUsersScore();
	}
}
