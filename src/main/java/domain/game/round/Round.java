package domain.game.round;

import java.util.List;

import domain.game.Table;
import domain.user.Gambler;

public abstract class Round {
	protected boolean playerWin;
	protected boolean dealerWin;
	protected List<Gambler> winnerList;

	abstract void run(Table table);
	abstract void findWinner(Table table);
	public boolean hasWinners(){
		return playerWin||dealerWin;
	}
}
