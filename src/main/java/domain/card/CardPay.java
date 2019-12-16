package domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardPay {
	private List<Card> cardList;

	public CardPay() {
		cardList = new ArrayList<Card>();
		cardList = CardFactory.create();
	}

	public void giveUserCard(){

	}
}
