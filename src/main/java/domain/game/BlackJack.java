package domain.game;

import java.util.List;

import controller.InputController;
import domain.game.round.BlackJackRound;
import domain.game.round.HitRound;

public class BlackJack {
	private Table table;
	private BlackJackRound blackJackRound;
	private HitRound hitRound;

	public BlackJack() {
		setUp();
		blackJackRound = new BlackJackRound();
		hitRound = new HitRound();
	}

	public void run() {
		blackJackRound.run(table);
		if (table.hasWinner()) {
			return;
		}
		hitRound.run(table);
	}

	private void setUp() {
		InputController inputController = InputController.getInputController();
		List<String> playerNames = inputController.getPlayerNames();
		List<Double> bettings = inputController.getBettings(playerNames);
		table = new Table(playerNames, bettings);
	}
}
