package domain.game;

import domain.user.UserRepository;
import domain.view.ViewInput;;

public class Game {
	private static Game blackJack = new Game();
	private UserRepository userRepository = new UserRepository();

	public static Game getInstance() {
		return blackJack;
	}

	public void run() {
		userRepository.makePlayerName(ViewInput.getPlayerNames());
		userRepository.makePlayerList();
		
	}
 }
