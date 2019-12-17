package domain.game.round;

import controller.OutputController;
import domain.game.Table;
import domain.user.Gambler;
import domain.user.Player;
import view.OutputView;

public abstract class Round {
	protected boolean playerWin;
	protected boolean dealerWin;

	public abstract void run(Table table);

	protected void checkPlayerWin(Table table) {
		playerWin = table.getPlayerList().stream()
			.anyMatch(Gambler::isWinner);
	}

	public boolean hasWinners() {
		return playerWin || dealerWin;
	}

	protected void printStatus(Table table) {
		OutputView outputView = OutputView.getInstance();
		outputView.printDealerResultLine(table.getDealer(), table.getDealer().sumCardsMax());
		for (Gambler player : table.getPlayerList()
		) {
			outputView.printPlayerResultLine((Player)player, player.sumCardsMax());
		}
		outputView.printNewLine();
	}

	protected abstract void doSettlement(Table table);
}
