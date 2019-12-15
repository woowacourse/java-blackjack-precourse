package domain.card;

import java.util.List;

public class Deck {
	private List<Card> cards;
	private DrawSequence drawSequence;

	public Deck() {
		cards = CardFactory.create();
		drawSequence = new DrawSequence(cards.size());
	}
	public void shuffle(){
		drawSequence.shuffle();
	}
	public Card draw() throws Exception {
		if (drawSequence.hasNext() == false) {
			throw new Exception();
		}
		int index = drawSequence.next();
		return cards.get(index);
	}
}
