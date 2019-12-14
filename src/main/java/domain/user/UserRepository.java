package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.view.ViewInput;

public class UserRepository {
	private List<String> playerNames = new ArrayList<String>();
	private List<Player> playerList = new ArrayList<Player>(); 
			
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

	public String getPlayerList() {
		String name = String.join(", ", playerNames);
		return name;
	}
}
