package domain.user;

import java.util.List;

public class PlayersConstraints {
	private static final int MAX_NUM_OF_PLAYERS = 6;
	
	public static void checkValidNames(List<String> playerNames) {
		checkValidEachName(playerNames);
		checkValidNumOfNames(playerNames);
	}
	
	private static void checkValidEachName(List<String> playerNames) {
		playerNames.stream()
			.forEach(name -> PlayerConstraints.checkValidName(name));
	}
	
	private static void checkValidNumOfNames(List<String> playerNames) {
		if (playerNames.size() > MAX_NUM_OF_PLAYERS) {
			throw new IllegalArgumentException("6명 이하의 이름을 입력해주세요");
		}
	}
	
	public static void checkValidMoney(List<Integer> bettingMoney) {
		checkValidEachMoney(bettingMoney);
	}
	
	private static void checkValidEachMoney(List<Integer> bettingMoney) {
		bettingMoney.stream()
			.forEach(eachMoney -> PlayerConstraints.checkValidMoney(eachMoney));
	}
}
