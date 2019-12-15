package domain.game;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import domain.user.Player;

public class GameRequest {
	private Scanner scan;
	private String name;
	private double money;
	private StringTokenizer usersToken;
	private ArrayList<Player> userList;
	private GameUI gameUI;

	public GameRequest() {
		scan = new Scanner(System.in);
		userList = new ArrayList<Player>();
		gameUI = new GameUI();
	}

	public ArrayList<Player> requestName() {
		gameUI.requestUserNameInterface();
		name = scan.nextLine();
		usersToken = new StringTokenizer(name);
		for (int i = 1; usersToken.hasMoreTokens(); i++) {
			String name = usersToken.nextToken(",");
			userList.add(new Player(name, requestBettingMoney(name)));
		}
		test();
		return userList;
	}
	private void test(){
		for (int i = 0; i < userList.size(); i++) {
			System.out.println(userList.get(i).getName() + "   " + userList.get(i).getMoney());
		}
	}
	private double requestBettingMoney(String name) {
		gameUI.requestUserBettingMoneyInterface(name);
		money = scan.nextInt();
		return money;
	}
}
