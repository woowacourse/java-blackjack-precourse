package domain.validity;

import domain.card.CardFactory;

public class ValidityCheck {
	private static boolean[] cardUsed = new boolean[CardFactory.cardSize];
	
	public String nameIsValid(String name) throws IllegalArgumentException {
		if (name.equals("")) {
			throw new IllegalArgumentException();
		}
		
		return name;
	}
	
	public int moneyIsValid(String money) throws IllegalArgumentException {
		if(money.equals("")) {
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
