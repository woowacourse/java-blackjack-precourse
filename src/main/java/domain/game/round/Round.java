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
	public boolean hasWinners(){
		return playerWin||dealerWin;
	}
	protected void showResultStatus(Table table) {
		OutputController outputController = OutputController.getOutputController();
		outputController.printDealerResultLine(table.getDealer(),table.getDealer().sumCardsMax());
		for (Gambler player: table.getPlayerList()
		) {
			outputController.printPlayerResultLine((Player)player,player.sumCardsMax());
		}
		outputController.printNewLine();
	}
}
