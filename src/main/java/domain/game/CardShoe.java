package domain.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;

/**
 * 카드 분배 역할을 하는 객체
 * @author smr60
 *
 */
public class CardShoe {
	private static final int TOP_OF_DUMMY = 0;
	private List<Card> cardDummy = new ArrayList<>(CardFactory.create());

	public CardShoe() {
		shuffleCardDummy();
	}

	private void shuffleCardDummy() {
		Collections.shuffle(cardDummy);
	}

	public Card getOneCard() {
		return cardDummy.remove(TOP_OF_DUMMY);
	}

}
