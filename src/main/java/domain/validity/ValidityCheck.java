package domain.validity;

import java.util.List;

import domain.card.CardFactory;

public class ValidityCheck {
	private static boolean[] cardUsed = new boolean[CardFactory.cardSize];
	
	public String nameIsValid(String name) throws IllegalArgumentException {
		if (name.equals("")) {
			throw new IllegalArgumentException();
		}
		return name;
	}
	
	public boolean checkPlayerNameList(List<String> playerNameList) {
		boolean rightToUse = true;
		
		for (String player : playerNameList) {
			rightToUse = properInput(rightToUse, player);
		}
		
		if (!rightToUse) {
			playerNameList.removeAll(playerNameList);
			return false;
		}
		return true;
	}
	
	public boolean properInput(boolean rightToUse, String player) {
		if(player.length() != 0 && rightToUse) {
			return true;
		}
		return false;
	}
	
	public int moneyIsValid(String money) throws IllegalArgumentException {
		if (money.equals("") || Integer.parseInt(money) == 0) {
			throw new IllegalArgumentException();
		}
		return Integer.parseInt(money);
	}
	
	public int cardIsUsed(int inx) throws Exception {
		if (cardUsed[inx]) {
			throw new Exception();
		}
		
		cardUsed[inx] = true;
		return inx;
	}
}
