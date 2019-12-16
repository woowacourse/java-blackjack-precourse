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
		boolean flag = true;
		
		for (String player : playerNameList) {
			flag = wrongInput(flag, player);
		}
		
		if (!flag) {
			playerNameList.removeAll(playerNameList);
			return false;
		}
		return true;
	}
	
	public boolean wrongInput(boolean flag, String player) {
		if(player.length() != 0 && flag) {
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
