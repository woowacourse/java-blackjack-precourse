package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.CardFactory;
import domain.game.Game;
import domain.view.ViewInput;

public class UserRepository {
	private final int firstCardCnt = 2;
	
	private Dealer dealer = new Dealer();
	private List<String> playerNames = new ArrayList<String>();
	private List<User> userList = new ArrayList<User>();
	private Game blackJack = Game.getInstance();
			
	public void makePlayerName(String name) {
		String[] names = name.split(",");
		
		for (String player : names) {
			playerNames.add(player.trim());
		}
	}
	
	public void makeUserList() {
		userList.add(dealer);
		
		for (String name : playerNames) {
			Player player = new Player(name, ViewInput.getBettingPrice(name));
			
			userList.add(player);
		}
	}

}
