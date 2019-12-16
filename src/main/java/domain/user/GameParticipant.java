package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Symbol;

public class GameParticipant {
	private final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}
	
	public String getInitialDealerCardInfo() {
		final int FIRST_INDEX = 0;
		return cards.get(FIRST_INDEX).toString();
	}
	
	public String getCardInfo() {
		String cardInfo = "";
		for (Card card : cards) {
			cardInfo += card.toString() + " ";
		}
		return cardInfo;
	}
	
	public int getCardScore() {
		int score = cards.stream()
						.mapToInt(card -> card.getScore())
						.sum();
		return changeAceScore(score);
	}

	/**
	 * 에이스 카드의 점수를 1 또는 11로 유연하게 사용
	 * 
	 * @param score
	 * @return
	 */
	private int changeAceScore(int score) {
		boolean hasAce = false;
		for (Card card : cards) {
			if (card.getSymbol().equals(Symbol.ACE)) {
				hasAce = true;
			}
		}
		if (hasAce && score <= Symbol.ACE.getScore() + Symbol.TEN.getScore()) {
			return score + Symbol.TEN.getScore();
		}
		return score;
	}
}
