package domain.game.round;

import domain.game.Table;
import view.OutputView;

public abstract class Round {
	public abstract void run(Table table);

	protected void printStatus(Table table) {
		OutputView outputView = OutputView.getInstance();
		outputView.printNewLine();
		outputView.printDealerCards(table.getDealer().getCardsText(), true);
		outputView.printResultLine(table.getDealer().sumMax());
		outputView.printPlayersCardsResultLine(table.getPlayerNames(), table.getPlayersCardText(),
			table.getPlayerResults());
		outputView.printNewLine();
	}

	protected abstract void doSettlement(Table table);

	protected void printEarnings(Table table) {
		OutputView outputView = OutputView.getInstance();
		outputView.printEarnings(table.getPlayerNames(), table.getPlayerEarnings(), table.getDealerShare());
	}
}
