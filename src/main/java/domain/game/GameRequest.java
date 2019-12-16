package domain.game;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import domain.user.Player;

public class GameRequest {
	private Scanner scan;
	private String name;
	private double money;
	private StringTokenizer usersToken;
	private ArrayList<Player> userList;
	private GameUI gameUI;
	private RequestException requestException;

	public GameRequest() {
		scan = new Scanner(System.in);
		userList = new ArrayList<Player>();
		gameUI = new GameUI();
		requestException = new RequestException();
	}

	public void requestName() {
		try {
			gameUI.requestUserNameInterface();
			name = scan.nextLine();
			usersToken = new StringTokenizer(name);
			tokenName(usersToken);
		} catch (Exception e) {
			scan = new Scanner(System.in);
			System.out.println("5글자 이하의 이름을 입력하세요");
			requestName();
		}
	}

	public ArrayList<Player> getUserList() {
		return userList;
	}

	private void tokenName(StringTokenizer usersToken) throws Exception {
		divideName(usersToken);
	}

	private void divideName(StringTokenizer usersToken) throws Exception {
		for (int i = 1; usersToken.hasMoreTokens(); i++) {
			String name = usersToken.nextToken(",");
			requestException.exceptionName(name);
			requestBettingMoney(name);
			userList.add(new Player(name, money));
		}
	}

	private void requestBettingMoney(String name) {
		try {
			gameUI.requestUserBettingMoneyInterface(name);
			money = scan.nextDouble();
		} catch (Exception e) {
			System.out.println("숫자를 입력하세요");
			scan = new Scanner(System.in);
			requestBettingMoney(name);
		}
	}
}
