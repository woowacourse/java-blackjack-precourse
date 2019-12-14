package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.game.Game;
import domain.view.ViewInput;

public class UserRepository {
	private final int playerInx = 1;
	
	private List<String> playerNames = new ArrayList<String>();
	private List<User> userList = new ArrayList<User>();
	private Game blackJack = Game.getInstance();
	private String players;
			
	public void makePlayerName(String name) {
		String[] names = name.split(",");
		players = String.join(", ", names);
		
		for (String player : names) {
			playerNames.add(player.trim());
		}
	}
	
	public void makeUserList() {
		Dealer dealer = new Dealer();
		userList.add(dealer);
		
		for (String name : playerNames) {
			Player player = new Player(name, ViewInput.getBettingPrice(name));
			
			userList.add(player);
		}
	}
	
	public List<User> getUserList() {
		return userList;
	}
	
	public String getPlayerList() {
		return players;
	}
	
	public void showFirstUserCard() {
		for (User user : userList) {
			user.showFirstResult();
		}
	}
}
