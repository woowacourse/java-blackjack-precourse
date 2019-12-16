package domain.game.round;

import java.util.Arrays;
import java.util.stream.Collectors;

import controller.OutputController;
import domain.game.Rule;
import domain.game.Table;
import domain.user.Gambler;
import domain.user.Player;

public class BlackJackRound extends Round {
	@Override
	public void run(Table table) {
		distribute(table, Rule.getBasicDraw());
		findWinner(table);
		showStatus(table);
		if(hasWinners()){
			showResultStatus(table);
		}
	}

	@Override
	void findWinner(Table table) {
		winnerList = table.getPlayerList().stream()
			.filter(player -> (player.sumCardsMax() == Rule.getBlackjackPoint()))
			.collect(Collectors.toList());
		if (winnerList.size() > 0) {
			playerWin = true;
		}
		if (table.getDealer().sumCardsMax() == Rule.getBlackjackPoint()) {
			dealerWin = true;
		}
	}

	private void distribute(Table table, int count) {
		try {
			table.drawAll(Arrays.asList(table.getDealer()), count);
			table.drawAll(table.getPlayerList(), count);
		} catch (Exception e) {
			System.out.println(Rule.getOutOfCardsMessage());
		}
	}

	private void showStatus(Table table) {
		OutputController outputController = OutputController.getOutputController();
		outputController.printBlackJackRoundTextLine(table.getPlayerNames(), Rule.getBasicDraw());
		outputController.printDealerCards(table.getDealer(),Rule.getOpenCount(),false);
		for (Gambler player: table.getPlayerList()
			 ) {
			outputController.printPlayerCards((Player)player);
			outputController.printNewLine();
		}
		outputController.printNewLine();
	}
}
