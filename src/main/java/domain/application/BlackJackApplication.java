package domain.application;

import domain.game.Game;
import domain.user.UserRepository;
import domain.validity.ValidityCheck;
import domain.view.ViewInput;

public class BlackJackApplication {

	public static void main(String[] args) {
		Game blackJack = Game.getInstance();
		UserRepository userRepository = blackJack.getUserRepository(); 
		ValidityCheck validity = new ValidityCheck();
		
		do {
			userRepository.makePlayerName(ViewInput.getPlayerNames());
		} while (!validity.checkPlayerNameList(userRepository.getPlayerNameList()));
		
		blackJack.run();
	}

}
