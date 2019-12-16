
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
	
	private List<Player> players = new ArrayList<>();
	private Input input = new Input();
	private String[] playerNames;

	public void startGame() {
		playerNames = input.getPlayerNames().split(COMMA);
		for (String name : playerNames) {
			makePlayers(name, input.getBattingMoney(name));
		}
	}
	
	private void makePlayers (String name, int battingMoney) {
		players.add(new Player(name, battingMoney));
	}
}
