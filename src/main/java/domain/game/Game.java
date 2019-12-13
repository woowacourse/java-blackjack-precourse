package domain.game;

import domain.user.PlayerRepository;
import domain.view.ViewInput;;

public class Game {
	private static Game blackJack = new Game();
	private PlayerRepository playerRepository = new PlayerRepository();

	public static Game getInstance() {
		return blackJack;
	}

	public void run() {
		playerRepository.makePlayerName(ViewInput.getPlayerNames());
		playerRepository.makePlayerList();
	}
 }
