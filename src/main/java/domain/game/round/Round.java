package domain.game.round;

import java.util.List;

import controller.OutputController;
import domain.game.Table;
import domain.user.Gambler;
import domain.user.Player;

public abstract class Round {
	protected boolean playerWin;
	protected boolean dealerWin;
	protected List<Gambler> winnerList;

	public abstract void run(Table table);

	abstract void findWinner(Table table);

	protected void checkDealerWin(Gambler dealer, int point) {
		dealerWin = (dealer.sumCardsMax() == point);
	}

	protected void checkPlayerWin() {
		playerWin = (winnerList.size() > 0);
	}

	public boolean hasWinners() {
		return playerWin || dealerWin;
	}

	protected void showResultStatus(Table table) {
		OutputController outputController = OutputController.getOutputController();
		outputController.printDealerResultLine(table.getDealer(), table.getDealer().sumCardsMax());
		for (Gambler player : table.getPlayerList()
		) {
			outputController.printPlayerResultLine((Player)player, player.sumCardsMax());
		}
		outputController.printNewLine();
	}
}
