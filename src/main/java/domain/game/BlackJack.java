package domain.game;

import config.BlackJackConfig;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import controller.BlackJackInitializingController;
import domain.user.Status;
import services.BlackJackUIService;
import utils.UtilityMethods;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {
	private List<Card> cardDeck;
	private List<Player> players;
	private Dealer dealer = this.dealer = new Dealer();
	private List<Integer> cardIndex = new ArrayList<Integer>();
	private List<Status> playersStatus = new ArrayList<Status>();
	private boolean gameStopper = false;

	public BlackJack() {
		List<Double> bettingList = new ArrayList<Double>();

		this.cardDeck = BlackJackInitializingController.initializeCardDeck();
		List<String> nameList = BlackJackUIService.printMessageToGetName();
		for (String name : nameList) {
			bettingList.add(BlackJackUIService.printMessageToGetBetting(name));
		}
		this.players = BlackJackInitializingController.initializePlayers(nameList, bettingList);
		cardIndex = BlackJackInitializingController
			.initializeCardIndexList(BlackJackConfig.MAX_CARD_INDEX);
		this.playersStatus = BlackJackInitializingController.initializePlayersStatus(this.players.size());
	}

	public void setGameStopper() {
		this.gameStopper = true;
	}

	public boolean isGameStopperFalse() {
		return gameStopper == false;
	}

	public Status getPlayersStatus(int index) {
		return playersStatus.get(index);
	}

	public void setPlayersStatus(Status playerStatus, int index) {
		this.playersStatus.remove(index);
		this.playersStatus.add(index, playerStatus);
	}

	public void showHandOfAllPlayer() {
		dealer.showHand();
		for (Player player : this.players) {
			player.showHand();
		}
		System.out.println("");
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
		for (int i = 0; i < this.players.size(); i++) {
			drawCardToPlayerByStatus(players.get(i), i);
		}
		judgeDealerToRedraw();
	}

	private void askPlayerToDrawCard(Player player, int index) {
		Boolean drawMore = BlackJackUIService.printInputForMoreCard(player.getName());
		if (drawMore == true) {
			player.addCard(popRandomCard());
			player.showHand();
		} else if (drawMore == false){
			this.setPlayersStatus(Status.BUSTED, index);
		}
	}

	private void judgeDealerToRedraw() {
		if (dealer.getScore() < BlackJackConfig.DEALER_DRAW_SCORE) {
			BlackJackUIService.printDealerDrawMessage();
			dealer.addCard(popRandomCard());
		} else if (dealer.getScore() >= BlackJackConfig.DEALER_DRAW_SCORE){
			setGameStopperIfDealerBusted();
		}
	}

	private void drawCardToPlayerByStatus(Player player, int index) {
		if (this.getPlayersStatus(index) == Status.KEEP_GO) {
			askPlayerToDrawCard(player, index);
			statusUpdate(index, player);
		}
	}

	private void statusUpdate(int index, Player player) {
		if (this.playersStatus.get(index) == Status.KEEP_GO) {
			this.setPlayersStatus(player.checkStatus(), index);
		}
	}

	public void setGameStopperIfBlackJack() {
		finishGameIfPlayerIsBlackJack();
		finishGameIfDealerIsBlackJack();
	}

	private void finishGameIfDealerIsBlackJack() {
		if (this.dealer.checkStatus() == Status.BLACKJACK) {
			this.setGameStopper();
		}
	}

	public void finishGameIfPlayerIsBlackJack() {
		if (playersStatus.contains(Status.BLACKJACK)) {
			setGameStopper();
		}
	}

	public void setGameStopperIfBusted() {
		setGameStopperIfDealerBusted();
		setGameStopperIfAllplayerBusted();
	}

	private void setGameStopperIfDealerBusted() {
		if (this.dealer.checkStatus() == Status.BUSTED) {
			this.setGameStopper();
		}
	}

	private void setGameStopperIfAllplayerBusted() {
		int bustedCount = 0;

		for (Status status : this.playersStatus) {
			bustedCount = getBustedCount(status, bustedCount);
		}
		if (bustedCount == this.players.size()) {
			this.setGameStopper();
		}
	}

	private int getBustedCount(Status status, int count) {
		if (status.equals(Status.BUSTED)) {
			count++;
		}
		return count;
	}

}
