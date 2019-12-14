package domain.user;

import domain.card.CardDeck;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
	public Dealer() {
	}

	public void giveCard(CardDeck cardDeck, User user) {
		user.addCard(cardDeck.draw());
	}
}
