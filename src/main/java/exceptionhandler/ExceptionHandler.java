
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
	private List<String> usedNames = new ArrayList<>();

	public boolean isDuplicated(String playerNames) {
		boolean status = false;
		for (String name : playerNames.split(",")) {
			status = isUsed(name);
		}
		return status;
	}

	private boolean isUsed(String name) {
		if (!(usedNames.contains(name))) {
			usedNames.add(name);
		}
		return usedNames.contains(name);
	}

	public boolean lessThanOne(String playerNames) {
		if (playerNames.split(",").length < 1) {
			return true;
		}
		return false;
	}

	public boolean moreThanEight(String playerNames) {
		if (playerNames.split(",").length > 8) {
			return true;
		}
		return false;
	}
}
