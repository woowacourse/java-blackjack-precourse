package controller;

import java.util.ArrayList;
import java.util.List;

import domain.card.CardDeck;
import domain.profit.ProfitCalculator;
import domain.rule.Rule;
import domain.user.Dealer;
import domain.user.Player;
import view.InputView;
import view.OutputView;

public class BlackjackController {
	private static final String HIT = "y";
	private static final int INIT_CARD_SIZE = 0;
	private static final int FIRST_DRAW_CARD_SIZE = 2;

	private final CardDeck cardDeck = new CardDeck();
	private final Dealer dealer = new Dealer();
	private final List<Player> players = new ArrayList<>();

	public BlackjackController() {
		initPlayers();
	}

	private void initPlayers() {
		String[] names = InputView.getPlayerNames();
		OutputView.printNewLine();
		for (String name : names) {
			double bettingMoney = InputView.getBettingMoney(name);
			players.add(new Player(name, bettingMoney));
			OutputView.printNewLine();
		}
	}

	private void giveTwoCard() {
		for (int i = INIT_CARD_SIZE; i < FIRST_DRAW_CARD_SIZE; i++) {
			dealer.giveCard(cardDeck, dealer);
			dealer.giveCard(cardDeck, players);
		}
	}

	private void printFaceUpCards() {
		OutputView.printCards(dealer.getName(), dealer.getFaceUpCards());
		for (Player player : players) {
			OutputView.printCards(player.getName(), player.getCards());
		}
		OutputView.printNewLine();
	}

	private boolean wantMoreCard(Player player) {
		return InputView.getMoreCard(player.getName()).equals(HIT);
	}

	private void getMoreCard(Player player) {
		while (Rule.canGetMoreCards(player) && wantMoreCard(player)) {
			dealer.giveCard(cardDeck, player);
			OutputView.printCards(player.getName(), player.getCards());
		}
	}

	private void getMoreCards() {
		players.forEach(this::getMoreCard);
		if (Rule.shouldAddCard(dealer)) {
			dealer.giveCard(cardDeck, dealer);
			OutputView.printNewLine();
			OutputView.printDealerAddCard(dealer.getName());
		}
		OutputView.printNewLine();
	}

	private void printScores() {
		OutputView.printScore(dealer);
		players.forEach(OutputView::printScore);
		OutputView.printNewLine();
	}

	private void printProfits() {
		ProfitCalculator calculator = new ProfitCalculator(dealer, players);
		OutputView.printProfits(calculator.getProfits());
	}

	private void runFirstPhase() {
		giveTwoCard();
		printFaceUpCards();
	}

	private void runSecondPhase() {
		if (Rule.isBlackjack(dealer)) {
			return;
		}
		getMoreCards();
	}

	private void runLastPhase() {
		printScores();
		printProfits();
	}

	public void run() {
		runFirstPhase();
		runSecondPhase();
		runLastPhase();
	}
}
