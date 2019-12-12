package domain.game;

import java.util.ArrayList;

import domain.view.ViewInput;

public class Game {
	static Game blackJack = new Game();
	private ArrayList<String> playerList = new ArrayList<String>();
	
	public static Game getInstance() {
		return blackJack;
	}

	public void run() {
		ViewInput.getPlayerNames();
	}
	
	public void makePlayers(String name) {
		String[] names = name.split(",");
		
		for(String player : names) {
			playerList.add(player);
		}
	}
	public void showPlayers() {
		for(String player : playerList) {
			System.out.println(player);
		}
	}
}
