package domain.game;

import java.util.ArrayList;
import java.util.List;

import controller.InputController;
import domain.user.Gambler;
import domain.user.PlayerFactory;
import view.InputView;

public class PlayerPhase {
	private InputController inputController;
	private InputView inputView;

	public PlayerPhase() {
		inputView = new InputView();
		inputController = new InputController(inputView);
	}

	public List<Gambler> gatherPlayers() {
		List<String> playerNames = inputController.getPlayerNames();
		List<Double> bettingList = gatherBetting(playerNames);
		return PlayerFactory.create(playerNames, bettingList);
	}

	private List<Double> gatherBetting(List<String> names) {
		List<Double> bettingList = new ArrayList<>();
		for (String name : names
		) {
			bettingList.add(inputController.getPlayerBetting(name));
		}
		return bettingList;
	}
}
