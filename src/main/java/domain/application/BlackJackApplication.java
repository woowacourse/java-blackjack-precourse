package domain.application;

import domain.game.Game;

public class BlackJackApplication {

	public static void main(String[] args) {
		Game blackJack = Game.getInstance();
		
		blackJack.run();
		blackJack.showPlayers();
	}

}
