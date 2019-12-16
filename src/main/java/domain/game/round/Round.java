package domain.game.round;

import java.util.List;

import controller.OutputController;
import domain.game.Table;
import domain.user.Gambler;
import domain.user.Player;

public abstract class Round {
	protected boolean playerWin;
	protected boolean dealerWin;
	protected Table table;

	public Round(Table table) {
		this.table = table;
	}

	public abstract void run();

	abstract void findWinner();

	protected void checkDealerWin(Gambler dealer, int point) {
		dealerWin = (dealer.sumCardsMax() == point);
	}

	protected void checkPlayerWin() {
		playerWin = table.getPlayerList().stream()
			.anyMatch(Gambler::isWinner);
	}

	public boolean hasWinners() {
		return playerWin || dealerWin;
	}

	protected void showStatus() {
		OutputController outputController = OutputController.getOutputController();
		outputController.printDealerResultLine(table.getDealer(), table.getDealer().sumCardsMax());
		for (Gambler player : table.getPlayerList()
		) {
			outputController.printPlayerResultLine((Player)player, player.sumCardsMax());
		}
		outputController.printNewLine();
	}

	protected abstract void doSettlement();
}
