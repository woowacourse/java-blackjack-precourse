package domain.user;

import java.util.List;

public class PlayersConstraints {
	private static final int MAX_NUM_OF_PLAYERS = 6;
	
	public static void checkValidEachName(List<String> playerNames) {
		playerNames.stream()
			.forEach(name -> Player.checkValidName(name));
	}
	
	public static void checkValidNumOfNames(List<String> playerNames) {
		if (playerNames.size() > MAX_NUM_OF_PLAYERS) {
			throw new IllegalArgumentException("6명 이하의 이름을 입력해주세요");
		}
	}
	
	public static void checkOverlappingNames(List<String> playerNames) {
		if (playerNames.stream().distinct().count() != playerNames.size()) {
			throw new IllegalArgumentException("중복되지 않은 이름들을 입력해주세요");
		}
	}
	
	public static void checkValidEachBettingMoney(List<Integer> bettingMoney) {
		bettingMoney.stream()
			.forEach(eachMoney -> Player.checkValidBettingMoney(eachMoney));
	}
}
