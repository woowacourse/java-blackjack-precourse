package domain.user;

import domain.card.Card;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends GameParticipant {

	public Dealer() {
	}

	@Override
	public String getCardInfo() {
		final int FIRST_INDEX = 0;
		return cards.get(FIRST_INDEX).toString();
	}

	@Override
	public String getFinalCardInfo() {
		String cardInfo = "";
		for (Card card : cards) {
			cardInfo += card.toString() + " ";
		}
		return cardInfo + "-" + getCardScore();
	}
}
