package domain.game;

import controller.BlackJackController;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import services.BlackJackInitializingService;
import services.BlackJackUIService;
import utils.UtilityMethods;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {
	private List<Card> cardDeck;
	private List<Player> players;
	private Dealer dealer;
	private List<Integer> cardIndex = new ArrayList<Integer>();
	static private int MAX_CARD_INDEX = 52;

	public BlackJack() {
		List<Double> bettingList = new ArrayList<Double>();

		this.cardDeck = BlackJackInitializingService.initializeCardDeck();
		List<String> nameList = BlackJackUIService.printMessageToGetName();
		for (String name : nameList) {
			bettingList.add(BlackJackUIService.printMessageToGetBetting(name));
		}
		this.players = BlackJackInitializingService.initializePlayers(nameList, bettingList);
		this.dealer = new Dealer();
		for (int i = 0; i < MAX_CARD_INDEX; i++) {
			cardIndex.add(i);
		}
	}

	public void showHandOfAllPlayer() {
		dealer.showHand();
		for (Player player : this.players) {
			player.showHand();
		}
	}

	public Card popRandomCard() {
		int randomNumber = UtilityMethods.generateRandomNumberUnder(this.cardIndex.size());
		int randomIndex = cardIndex.remove(randomNumber);
		return this.cardDeck.get(randomIndex);
	}

	public void drawCardDeckToAll() {
		for (Player player : this.players) {
			player.addCard(popRandomCard());
		}
		dealer.addCard(popRandomCard());
	}

	public void drawCardInMidGame() {
		for (Player player : this.players) {
			askPlayerToDrawCard(player);
		}
	}

	public void askPlayerToDrawCard(Player player) {
		if (BlackJackUIService.printInputForMoreCard(player.getName())) {
			player.addCard(popRandomCard());
		}
	}


}
