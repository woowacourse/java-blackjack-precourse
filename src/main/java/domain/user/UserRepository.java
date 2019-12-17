package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.game.Game;
import domain.view.ViewInput;

public class UserRepository {
	private final int dealerInx = 0;
	private final int playerFirstInx = 1;
	
	private List<String> playerNameList = new ArrayList<String>();
	private List<User> userList = new ArrayList<User>();
	private List<Double> profitList = new ArrayList<Double>();
			
	public void makePlayerName(String name) {
		String[] names = name.split(",", -1);
		
		for (String player : names) {
			playerNameList.add(ViewInput.getPlayerName(player.trim()));
		}
		System.out.println();
	}
	
	public List<String> getPlayerNameList() {
		return playerNameList;
	}
	
	public void makeUserList() {
		Dealer dealer = new Dealer();
		userList.add(dealer);
		
		for (String name : playerNameList) {
			Player player = new Player(name, ViewInput.getBettingPrice(name));
			
			userList.add(player);
		}
		System.out.println();
	}
	
	public List<User> getUserList() {
		return userList;
	}
	
	public String getPlayerNames() {
		String playerNames = playerNameList
				.stream()
				.collect(Collectors.joining(", "));
		
		return playerNames;
	}
	
	public void makeProfitList() {
		int criteria = userList.get(dealerInx).getScore();
		
		for (int i = 0; i < userList.size(); i++) {
			profitList.add((double)0);
		}
		
		for (int i = playerFirstInx; i < userList.size(); i++) {
			Game.getInstance().compareScore(i, criteria);
		}
	}
	
	public List<Double> getProfitList() {
		return profitList;
	}
}