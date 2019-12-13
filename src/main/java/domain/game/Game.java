package domain.game;

import java.util.ArrayList;

import domain.user.Player;
import domain.view.ViewInput;

public class Game {
	private static Game blackJack = new Game();
	
	private ArrayList<String> playerNames = new ArrayList<String>();
	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	public static Game getInstance() {
		return blackJack;
	}

	public void run() {
		makePlayerName(ViewInput.getPlayerNames());
		makePlayerList();
	}
	
	public void makePlayerName(String name) {
		String[] names = name.split(",");
		
		for (String player : names) {
			playerNames.add(player.trim());
		}
	}
	
	public void makePlayerList() {
		for (String name : playerNames) {
			Player player = new Player(name, ViewInput.getBettingPrice(name));
			
			playerList.add(player);
		}
	}
	
 }
