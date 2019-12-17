package domain.main;

import java.util.ArrayList;
import java.util.List;

import domain.user.Player;
import domain.main.Main;

public class InputHandler {
	
	static List<String> nameStringList = new ArrayList<>();
	static List<Double> bettingMoneyDoubleList = new ArrayList<>();

	
	public static void nameHandler() {
		String names = Template.nameRequest();
		List<String> nameList = new ArrayList<>();
		String[] nameArray = names.split(",");
		for (String name : nameArray) {
			nameList.add(name.trim());
		}
		int errorCode = nameHandlerException(nameList);
		nameHandlerResult(errorCode, nameList);
	}
	
	public static int nameHandlerException(List<String> nameList) {
		int error = 0;
		if (nameList.size() > 8) {
			System.out.println("게임 인원이 " + Main.maxPlayerNumber() + "명을 초과했습니다."); error = 1;
		}
		if (nameList.contains("")) {
			System.out.println("빈칸은 입력할 수 없습니다."); error = 2;
		}
		return error;
	}
	
	public static void nameHandlerResult(int error, List<String> nameList) {
		if (error != 0) {
			nameHandler();
		}
		if (error == 0) {
			nameStringList = nameList;
		}
	}
	
	
	
	
	public static void bettingMoneyHandler(List<String> nameList) {
		for (String name : nameList) {
			bettingMoneyHandlerException(name);
		}
	}
	
	public static void bettingMoneyHandlerException(String name) {
		int error = 0;
		Double bettingMoney = 1.0;
		String bettingMoneyString = Template.bettingMoneyRequest(name);
		try {
			bettingMoney = Double.parseDouble(bettingMoneyString);
		} catch(Exception e) {
			System.out.println("숫자를 입력해주세요."); error = 2;
		}
		bettingMoneyHandlerResult(error, bettingMoney, name);
	}
	
	public static void bettingMoneyHandlerResult(int error, Double bettingMoney, String name) {
		if (bettingMoney <= 0) {
			System.out.println("0보다 큰 금액을 입력해주세요."); error = 1;
		}
		if (error != 0) {
			bettingMoneyHandlerException(name);
		}
		if (error == 0) {
			bettingMoneyDoubleList.add(bettingMoney);
		}
	}
	
	
	
	
	public static List<Player> makePlayer() {
		List<Player> playerList = new ArrayList<>();
		int count = 0;
		for (String name : nameStringList) {
			Double bettingMoney = bettingMoneyDoubleList.get(count);
			playerList.add(new Player(name, bettingMoney));
			count += 1;
		}
		return playerList;
	}
	
	
	
	public static void oneMoreCardOrNot(List<Player> playerList) {
		for (Player player : playerList) {
			oneMoreCardOrNotException(player);
		}
	}
	
	public static void oneMoreCardOrNotException(Player player) {
		String answer = Template.oneMoreCardRequest(player);
		if (answer.charAt(0) == 'y' && answer.length() == 1) {
			Main.giveOneCardToPlayer(player);
			System.out.printf(player.name()+ " : " + player.showCard() + " - " + player.showScore() + "\n\n");
			oneMoreCardOrNotControl(player);
		}
		if (answer.length() != 1 || (answer.charAt(0) != 'y' && answer.charAt(0) != 'n') ) {
			System.out.println("y 또는 n만 입력이 가능합니다.");
			oneMoreCardOrNotControl(player);
		}
	}
	
	public static void oneMoreCardOrNotControl(Player player) {
		if (player.showScore() <= 21) {
			oneMoreCardOrNotException(player);
		}
	}
}
