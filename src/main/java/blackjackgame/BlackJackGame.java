
/*
 * ClassName : BlackJackGame
 * 
 * version : 0.1
 * 
 * date : 2019.12.15
 * 
 * author : ParkDooWon
 */

package blackjackgame;

import java.util.ArrayList;
import java.util.List;

import domain.user.Player;
import view.Input;

public class BlackJackGame {
	private static final String COMMA = ",";
	
	private Input input = new Input();
	private String[] playerNames;

	public void startGame() {
		playerNames = input.getPlayerNames().split(COMMA);
	}

}
