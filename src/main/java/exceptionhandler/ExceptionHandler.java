
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

public class ExceptionHandler {
	private static final String COMMA = ",";
	private static final int MIN_PLAYER = 1;
	private static final int MAX_PLAYER = 8;
	private static final int MIN_BATTING_MONEY = 1;
	private static final int MULTIPLE_OF_TEN = 0;

	public boolean isDuplicated(String playerNames) {
		List<String> names = new ArrayList<>();
		for (String name : playerNames.split(COMMA)) {
			names.add(name);
		}
		return names.size() != names.stream().distinct().count();
	}

	public boolean lessThanOne(String playerNames) {
		if (playerNames.split(COMMA).length < MIN_PLAYER) {
			return true;
		}
		return false;
	}

	public boolean moreThanEight(String playerNames) {
		if (playerNames.split(COMMA).length > MAX_PLAYER) {
			return true;
		}
		return false;
	}
	
	public boolean isPositiveNum(String battingMoney) {
		return Integer.valueOf(battingMoney) < MIN_BATTING_MONEY;
	}
	
	public boolean isNumber(String battingMoney) {
		boolean status = false;
		List<String> money = new ArrayList<>();
		for (int i = 0; i < battingMoney.length(); i++) {
			money.add(String.valueOf(battingMoney.charAt(i)));
		}
		/*
		 * 추후 구현
		 */
		return status;
	}
	
	public boolean isMultipleOfTen(String battingMoney) {
		if (Integer.valueOf(battingMoney) % 10 == MULTIPLE_OF_TEN) {
			return false;
		}
		return true;
	}

	
}
