package domain.game;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import domain.card.CardPay;
import domain.user.Dealer;
import domain.user.Person;
import domain.user.Player;

/**
 * GameRequest
 * 버전 : 1.0
 * 게임에서 요구되는 것을 처리하는 클래스
 */
public class GameRequest {
	private Scanner scan;
	private String name;
	private double money;
	private StringTokenizer usersToken;
	private ArrayList<Player> userList;
	private RequestException requestException;

	public GameRequest() {
		scan = new Scanner(System.in);
		userList = new ArrayList<Player>();
		requestException = new RequestException();
	}

	public void requestName() {
		try {
			GameUI.requestUserNameInterface();
			name = scan.nextLine();
			usersToken = new StringTokenizer(name);
			tokenName(usersToken);
		} catch (Exception e) {
			scan = new Scanner(System.in);
			GameUI.requestUserNameFixInterface();
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
			GameUI.requestUserBettingMoneyInterface(name);
			money = scan.nextDouble();
			requestException.exceptionMoney(money);
			scan = new Scanner(System.in);
		} catch (Exception e) {
			GameUI.requestUserBettingMoneyFixInterface();
			scan = new Scanner(System.in);
			requestBettingMoney(name);
		}
	}
	public void requestDealerAddCard(CardPay cardPay, Dealer dealer){

	}
	public void requestPlayerAddCard(CardPay cardPay, Player player) {
		String req;
		try {
			GameUI.printRequestAddCard(player.getName());
			requestException.exceptionAddCard(req = scan.nextLine(), player);
			cardPay.giveUserCard(req, player);
		} catch (Exception e) {
			System.out.println("제대로 된 문자를 입력하세요");
			scan = new Scanner(System.in);
			requestPlayerAddCard(cardPay, player);
		}
	}
}
