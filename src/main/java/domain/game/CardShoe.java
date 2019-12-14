package domain.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;

public class CardShoe {
	private static final int TOP_OF_DUMMY = 0;
	private List<Card> cardDummy = new ArrayList<>(CardFactory.create());

	public CardShoe() {
		shuffleCardDummy();
	}

	public void shuffleCardDummy() {
		Collections.shuffle(cardDummy);
	}

	public Card getOneCard() {
		return cardDummy.remove(TOP_OF_DUMMY);
	}

}
