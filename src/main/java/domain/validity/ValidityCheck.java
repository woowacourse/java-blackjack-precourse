package domain.validity;

import domain.card.CardFactory;

public class ValidityCheck {
	private boolean[] cardUsed = new boolean[CardFactory.cardSize];
	
	public String nameIsValid(String name) throws IllegalArgumentException {
		if (name.equals("")) {
			throw new IllegalArgumentException();
		}
		
		return name;
	}
	
	public int moneyIsValid(int money) throws IllegalArgumentException {
		return money;
	}
	
	public int  cardIsUsed(int inx) throws Exception {
		if (cardUsed[inx]) {
			throw new Exception();
		}
		
		return inx;
	}
}
