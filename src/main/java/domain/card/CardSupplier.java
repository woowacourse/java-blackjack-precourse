package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardSupplier {

	public List<Card> cards;

	public CardSupplier(List<Card> cards) {
		this.cards = new ArrayList<>(cards); // 수정 가능한 배열로 변형
		shuffleCards();
	}

	public Card getDeal() {
		return this.cards.remove(0);
	}

	private void shuffleCards() {
		Collections.shuffle(this.cards);
	}
}
