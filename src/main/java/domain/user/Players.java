package domain.user;

import java.util.List;
import java.util.ArrayList;

public class Players {
	private static final int MAX_NUM_OF_PLAYERS = 6;
	
	private List<Player> players = new ArrayList<Player>();
	
	public Players() {
		
	}
	
	public static void checkValidNames(List<String> playerNames) {
		checkValidEachName(playerNames);
		checkValidNumOfNames(playerNames);
	}
	
	private static void checkValidEachName(List<String> playerNames) {
		playerNames.stream()
			.forEach(name -> Player.checkValidName(name));
	}
	
	private static void checkValidNumOfNames(List<String> playerNames) {
		if (playerNames.size() > MAX_NUM_OF_PLAYERS) {
			throw new IllegalArgumentException("6명 이하의 이름을 입력해주세요");
		}
	}
}
