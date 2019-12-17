package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardSupplier {
	private static final int MINIMUM_CARD_SIZE = 1;
	private static final int FIRST_CARD_IN_SUPPLIER = 0;
	private static final String CARD_SUPPLIER_EMPTY = "52장의 카드를 모드 소진해서 다시 생성합니다.";

	private List<Card> cards;

	public CardSupplier() {
		getRandomCardList();
	}

	public Card getDeal() {
		if (cards.size() >= MINIMUM_CARD_SIZE) {
			return cards.remove(FIRST_CARD_IN_SUPPLIER);
		}
		System.out.println(CARD_SUPPLIER_EMPTY);
		getRandomCardList();
		return getDeal();
	}

	private void shuffleCards() {
		Collections.shuffle(cards);
	}

	private void getRandomCardList() {
		cards = new ArrayList<>(CardFactory.create());  // 수정 가능한 배열로 변형
		shuffleCards();
	}
}
