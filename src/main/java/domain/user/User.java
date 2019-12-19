package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class User {
	private static final int INIT_CARD_SIZE = 2;
	private static final int BLACKJACK_SCORE = 21;
	private static final int BURST_SCORE = 22;
	private static final int ACE_BONUS_SCORE = 10;
	private static final String COMMA = ",";
	protected static final String DELIMITER = ": ";

	private final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	public boolean bust() {
		return sumCardScore() >= BURST_SCORE;
	}

	public int sumCardScore() {
		int sumAceEleven = sumScore() + ACE_BONUS_SCORE;
		if (containAce() && sumAceEleven < BURST_SCORE) {
			return sumAceEleven;
		}
		return sumScore();
	}

	private int sumScore() {
		int sum = cards.stream().map(Card::getScore).reduce(Integer::sum).get();
		return sum;
	}

	private boolean containAce() {
		return cards.stream().anyMatch(Card::isAce);
	}

	public boolean isBlackJack() {
		if (cards.size() == INIT_CARD_SIZE && containAce() && sumCardScore() == BLACKJACK_SCORE) {
			return true;
		}
		return false;
	}

	public String getCards() {
		List<String> cardList = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			cardList.add(card.getCardSymbolAndType());
		}
		sb.append(String.join(COMMA, cardList));
		return sb.toString();
	}

}
