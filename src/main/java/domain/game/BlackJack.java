package domain.game;

import java.util.List;

import controller.InputController;
import controller.OutputController;

public class BlackJack {
	private Table table;

	public BlackJack() {
		setUp();
	}

	public void run() {


	}

	private void setUp() {
		InputController inputController=InputController.getInputController();
		List<String> playerNames = inputController.getPlayerNames();
		List<Double> bettings = inputController.getBettings(playerNames);
		table = new Table(playerNames, bettings);
	}
}
