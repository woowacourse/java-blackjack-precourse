package domain.game;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import controller.BlackJackInitializingController;
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

		this.cardDeck = BlackJackInitializingController.initializeCardDeck();
		List<String> nameList = BlackJackUIService.printMessageToGetName();
		for (String name : nameList) {
			bettingList.add(BlackJackUIService.printMessageToGetBetting(name));
		}
		this.players = BlackJackInitializingController.initializePlayers(nameList, bettingList);
		this.dealer = new Dealer();
		cardIndex = BlackJackInitializingController.initializecardIndexList(MAX_CARD_INDEX);
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
