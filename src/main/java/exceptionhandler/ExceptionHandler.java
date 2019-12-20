
/*
 * ClassName : Input
 * 
 * version : 0.1
 * 
 * date : 2019.12.16
 * 
 * author : ParkDooWon
 */

package exceptionhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExceptionHandler {
	private static final int MAX_PLAYER = 8;
	private static final int MIN_PLAYER = 1;
	
	private Scanner sc = new Scanner(System.in);
	
	private List<String> playerNames = new ArrayList<>();
	
	public boolean isNull(String names) {
		playerNames.clear();
		for (String name : names.split(",")) {
			playerNames.add(name);
		}
		return playerNames.stream().anyMatch(name -> name == null);
	}
	
	public boolean moreThanEight(String names) {
		return names.split(",").length > MAX_PLAYER;
	}
	
	public boolean isDuplicated(String names) {
		return playerNames.stream().distinct().count() != playerNames.size();
	}
	
	public boolean isMultipleOfTen(double bettingMoney) {
		if (bettingMoney % 10 == 0) {
			return false;
		}
		return true;
	}
	
	public boolean isNotValidAnswer(String moreCard) {
		if (moreCard.equals("y") || moreCard.equals("n")) {
			return false;
		}
		return true;
	}
	
}
