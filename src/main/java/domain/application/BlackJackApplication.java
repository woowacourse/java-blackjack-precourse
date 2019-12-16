package domain.application;

import domain.game.Game;
import domain.user.UserRepository;
import domain.view.ViewInput;

public class BlackJackApplication {

	public static void main(String[] args) {
		Game blackJack = Game.getInstance();
		UserRepository userRepository = blackJack.getUserRepository(); 
		
		do {
			userRepository.makePlayerName(ViewInput.getPlayerNames());
			
		} while (!userRepository.checkPlayerNameList());
		
		blackJack.run();
	}

}
