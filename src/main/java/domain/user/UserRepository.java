package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.view.ViewInput;
import domain.view.ViewOutput;

public class UserRepository {
	private final int playerFirstInx = 1;
	
	private List<String> playerNameList = new ArrayList<String>();
	private List<User> userList = new ArrayList<User>();
			
	public void makePlayerName(String name) {
		String[] names = name.split(",");
		
		for (String player : names) {
			playerNameList.add(player.trim());
		}
	}
	
	public void makeUserList() {
		Dealer dealer = new Dealer();
		userList.add(dealer);
		
		for (String name : playerNameList) {
			Player player = new Player(name, ViewInput.getBettingPrice(name));
			
			userList.add(player);
		}
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
	
	public void showFirstUserCard() {
		for (User user : userList) {
			user.showFirstResult();
		}
	}
	
	public void secondDealOutCheck() {
		for (int i = playerFirstInx; i < userList.size(); i++) {
			User user = userList.get(i);
			checkAnswer(user);
		}
	}
	
	public void checkAnswer(User user) {
		String answer = "y";
		
		while(answer.equals("y") && ((Player)user).isBelowJack()) {
			answer = ViewInput.askGetCard((Player)user);
			ViewOutput.showEachResult(user);
		}
	}
}
