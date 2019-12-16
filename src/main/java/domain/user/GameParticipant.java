package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Symbol;

/**
 * 딜러와 플레이어의 퍼블릭 인터페이스 역할을 하는 상위 객체
 * @author smr60
 *
 */
public abstract class GameParticipant {
	protected final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
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
	 * @return 변경 후 점수
	 */
	private int changeAceScore(int score) {
		boolean hasAce = false;
		for (Card card : cards) {
			hasAce = card.isSymbolAce();
		}
		if (hasAce && score <= Symbol.ACE.getScore() + Symbol.TEN.getScore()) {
			return score + Symbol.TEN.getScore();
		}
		return score;
	}
	
	abstract public String getCardInfo(); 

	abstract public String getFinalCardInfo();
	
}
