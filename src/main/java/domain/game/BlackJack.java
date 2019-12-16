package domain.game;

import controller.BlackJackController;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import services.BlackJackInitializingService;
import services.BlackJackUIService;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {
	private List<Card> cardDeck;
	private List<Player> players;
	private Dealer dealer;

	public BlackJack() {
		List<Double> bettingList = new ArrayList<Double>();

		this.cardDeck = BlackJackInitializingService.initializeCardDeck();
		List<String> nameList = BlackJackUIService.printMessageToGetName();
		for (String name : nameList) {
			bettingList.add(BlackJackUIService.printMessageToGetBetting(name));
		}
		this.players = BlackJackInitializingService
			.initializePlayers(nameList, bettingList);
		this.dealer = new Dealer();
	}

	public void drawCardDeckToAll() {
		for (Player player : this.players) {
			player.addCard(BlackJackController.popRandomCard(this.cardDeck));
		}
		dealer.addCard(BlackJackController.popRandomCard(this.cardDeck));
	}

	public void drawCardInMidGame() {
		for (Player player : this.players) {
			askPlayerToDrawCard(player);
		}
	}

	public void askPlayerToDrawCard(Player player) {
		if (BlackJackUIService.printInputForMoreCard(player.getName())) {
			player.addCard(BlackJackController.popRandomCard(this.cardDeck));
		}
	}


}
