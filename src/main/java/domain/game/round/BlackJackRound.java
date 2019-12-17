package domain.game.round;

import java.util.Arrays;

import domain.game.Rule;
import domain.game.Table;
import domain.user.Gambler;
import domain.user.Player;
import view.OutputView;

public class BlackJackRound extends Round {
	@Override
	public void run(Table table) {
		distribute(table);
		table.setWinners(Rule.BLACKJACK_POINT);
		printStatus(table);
		if (table.hasWinner()) {
			super.printStatus(table);
			doSettlement(table);
		}
	}

	@Override
	protected void printStatus(Table table) {
		OutputView outputView = OutputView.getInstance();
		outputView.printBlackJackRoundLine(table.getPlayerNames(), Rule.BASIC_DRAW);
		outputView.printDealerCards(Arrays.asList(table.getDealerCardText()).subList(0,Rule.DEALER_OPEN_COUNT), false);
		outputView.printNewLine();
		outputView.printPlayerCards(table.getPlayerNames(),table.getPlayersCardText());
		outputView.printNewLine();
	}

	@Override
	protected void doSettlement(Table table) {
		if (table.isDealerWin()) {
			table.doSettlement(Rule.RATIO_PUSH,Rule.RATIO_LOSE);
			return;
		}
		table.doSettlement(Rule.RATIO_WIN_BLACKJACK, Rule.RATIO_PUSH);
	}

	private void distribute(Table table) {
		try {
			table.drawDealer(Rule.BASIC_DRAW);
			table.drawAll(table.getPlayerList(), Rule.BASIC_DRAW);
		} catch (Exception e) {
			System.out.println(Rule.OUT_OF_CARDS_MESSAGE);
		}
	}
}
