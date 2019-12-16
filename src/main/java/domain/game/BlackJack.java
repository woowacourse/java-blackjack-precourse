package domain.game;

import controller.BlackJackController;
import domain.card.Card;
import domain.card.CardFactory;
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
		List<String> nameList = BlackJackUIService.getPlayersName();
		for (String name : nameList) {
			bettingList.add(BlackJackUIService.getPlayersBetting(name));
		}
		this.players = BlackJackInitializingService
			.initializePlayers(nameList, bettingList);
		this.dealer = new Dealer();
	}

	private void drawFromCardDeck() {
		for (Player player : this.players) {
			player.addCard(BlackJackController.popRandomCard(this.cardDeck));
		}
		dealer.addCard(BlackJackController.popRandomCard(this.cardDeck));
	}

	
}
