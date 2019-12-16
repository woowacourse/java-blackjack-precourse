package controller;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.result.GameResult;
import domain.result.Result;
import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Player;
import view.InputOutputView;

public class Game {
	private static Dealer dealer = new Dealer();
	private static List<Player> players = new ArrayList<>();
	private static List<Card> cards = CardFactory.create();
	private static List<Card> usedCards = new ArrayList<>();
	private static List<Result> results = new ArrayList<>();
	private static String users;
	private static double dealerBenefit = 0;

	public void start() {
		init();
		cardDistribute();
		showCards();
		askMoreCards();
		showCardsWithScore();
		calculateWinner();
		showResults();
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

	private void giveCard(int numberOfCards, Gamer gamer) {
		int randomNumber = (int) ((Math.random() * 1000) % (13 * 4));
		if (numberOfCards != 0 && usedCards.contains(cards.get(randomNumber))) {
			giveCard(numberOfCards, gamer);
		}
		if (numberOfCards != 0 && !usedCards.contains(cards.get(randomNumber))) {
			gamer.addCard(cards.get(randomNumber));
			usedCards.add(cards.get(randomNumber));
			giveCard(numberOfCards - 1, gamer);
		}
	}

	private void showCards() {
		InputOutputView.outputShowCards(dealer);
		for (Player player : players) {
			InputOutputView.outputShowCards(player);
		}
	}

	private void showCardsWithScore() {
		InputOutputView.outputShowCardsWithScore(dealer);
		for (Player player : players) {
			InputOutputView.outputShowCardsWithScore(player);
		}
	}

	private void askMoreCards() {
		for (Player player : players) {
			checkMoreCards(player);
		}
		checkMoreCards(dealer);
	}

	private void checkMoreCards(Player player) {
		int moreCardCount;
		if (player.isBlackJack() == true) {
			InputOutputView.outputNoMoreCards(player);
			return;
		}
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

	private void checkMoreCards(Dealer dealer) {
		while (dealer.getScoreAceAsEleven() <= 16) {
			giveCard(1, dealer);
			InputOutputView.outputDealerGotCard();
		}
		while (dealer.getScoreAceAsEleven() > 21 && dealer.getScoreAceAsOne() <= 16) {
			giveCard(1, dealer);
			InputOutputView.outputDealerGotCard();
		}
	}

	private void calculateWinner() {
		for (Player player : players) {
			checkWinner(player);
		}
		for (Result result : results) {
			dealerBenefit -= result.getBenefit();
		}
	}

	private void checkWinner(Player player) {
		if (checkUserWin(player) == true) {
			return;
		}
		if (checkUserLose(player) == true) {
			return;
		}
		if (checkTie(player) == true) {
			return;
		}
	}

	private boolean checkUserWin(Player player) {
		if (player.isBlackJack() == true && dealer.isBlackJack() == false) {
			return results.add(new Result(player, GameResult.USER_WIN_WITH_BLACKJACK));
		}
		if (dealer.isBust() == true) {
			return results.add(new Result(player, GameResult.USER_WIN));
		}
		if (player.isBust() == false && player.getBestScore() > dealer.getBestScore()) {
			return results.add(new Result(player, GameResult.USER_WIN));
		}
		return false;
	}

	private boolean checkUserLose(Player player) {
		if (player.isBust() == true) {
			return results.add(new Result(player, GameResult.USER_LOSE));
		}
		if (player.getBestScore() < dealer.getBestScore()) {
			return results.add(new Result(player, GameResult.USER_LOSE));
		}
		return false;
	}

	private boolean checkTie(Player player) {
		if (player.isBlackJack() == true && dealer.isBlackJack() == true) {
			return results.add(new Result(player, GameResult.TIE_GAME));
		}
		if (player.getBestScore() == dealer.getBestScore()) {
			return results.add(new Result(player, GameResult.TIE_GAME));
		}
		return false;
	}

	private void showResults() {
		InputOutputView.outputShowResults(dealerBenefit, results);
	}
}
