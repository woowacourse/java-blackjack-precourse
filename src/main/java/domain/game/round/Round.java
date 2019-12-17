package domain.game.round;

import domain.game.Table;
import domain.user.Gambler;
import view.OutputView;

public abstract class Round {
	public abstract void run(Table table);

	protected void printStatus(Table table) {
		OutputView outputView = OutputView.getInstance();
		outputView.printDealerCards(table.getDealer().getCardsText(),true);
		outputView.printResultLine(table.getDealer().getSum());
		outputView.printPlayersCardsResult(table.getPlayerNames(),table.getPlayersCardText(),table.getPlayerResults());
		outputView.printNewLine();
	}

	protected abstract void doSettlement(Table table);
}
