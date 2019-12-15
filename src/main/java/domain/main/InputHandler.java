package domain.main;

import java.util.ArrayList;
import java.util.List;

import domain.user.Player;

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
			System.out.println("게임 인원이 8명을 초과했습니다."); error = 1;
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
	
	
	
	
	public static void makePlayer() {
		List<Player> playerList = new ArrayList<>();
		int count = 0;
		for (String name : nameStringList) {
			Double bettingMoney = bettingMoneyDoubleList.get(count);
			playerList.add(new Player(name, bettingMoney));
			count += 1;
		}
	}
}
