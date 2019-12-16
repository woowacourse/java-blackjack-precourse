package controller;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.user.Gambler;
import domain.user.Player;
import view.OutputView;

public class OutputController {
	private static OutputController outputController;
	private OutputView outputView;

	private OutputController() {
		outputView = new OutputView();
	}

	public static OutputController getOutputController() {
		if (outputController == null) {
			outputController = new OutputController();
		}
		return outputController;
	}

	public void printPlayerCards(Player player) {
		List<String> cardsData = player.getCards().stream()
			.map(Card::getCardData)
			.collect(Collectors.toList());
		outputView.printPlayerCards(cardsData, player.getName());
	}

	public void printDealerCards(Gambler dealer, int open, boolean withCardText) {
		List<String> cardsData = dealer.getCards().stream()
			.map(Card::getCardData)
			.collect(Collectors.toList())
			.subList(0, open);
		outputView.printDealerCards(cardsData, withCardText);
	}

	public void printPlayerResultLine(Player player, int result) {
		printPlayerCards(player);
		outputView.printResultLine(result);
	}

	public void printDealerResultLine(Gambler dealer, int result) {
		printDealerCards(dealer, dealer.getCards().size(), true);
		outputView.printResultLine(result);
	}

	public void printDealerDrawLine(int dealerDrawPoint) {
		outputView.printDealerDrawLine(dealerDrawPoint);
	}

	public void printBlackJackRoundTextLine(List<String> playerNames, int basicDraw) {
		outputView.printBlackJackRoundLine(playerNames, basicDraw);
	}

	public void printNewLine() {
		outputView.printNewLine();
	}
}
