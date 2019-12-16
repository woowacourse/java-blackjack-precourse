package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.game.Game;
import domain.view.ViewInput;
import domain.view.ViewOutput;

public class UserRepository {
	private final int playerFirstInx = 1;
	private final int dealerInx = 0;
	
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
	
	public void secondPlayerDealOut() {
		for (int i = playerFirstInx; i < userList.size(); i++) {
			User user = userList.get(i);
			checkAnswer(user);
		}
	}
	
	public void checkAnswer(User user) {
		String answer = "y";
		
		while (answer.equals("y") && ((Player)user).isBelowJack()) {
			answer = ViewInput.askGetCard((Player)user);
			ViewOutput.showEachResult(user);
			System.out.println();
		}
	}
	
	public void secondDealerDealOut() {
		Dealer dealer = (Dealer)userList.get(dealerInx);
		
		while (dealer.isBelowCriteria()) {
			dealer.addCard(Game.getInstance().selectedCard());
			System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
		}
	}
	
	public void showAllResult() {
		for (User user : userList) {
			ViewOutput.showEachResult(user);
			System.out.println(" - 결과 : " + user.getScore());
		}
	}
}
