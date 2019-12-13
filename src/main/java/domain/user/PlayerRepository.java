package domain.user;

import java.util.ArrayList;

import domain.view.ViewInput;

public class PlayerRepository {
	private ArrayList<String> playerNames = new ArrayList<String>();
	private ArrayList<Player> playerList = new ArrayList<Player>();
	
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
