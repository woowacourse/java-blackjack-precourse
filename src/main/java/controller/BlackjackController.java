package controller;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Player;
import view.InputView;

public class BlackjackController {
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

	public void run() {
		giveTwoCard();
	}
}
