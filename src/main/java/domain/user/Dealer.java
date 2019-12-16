package domain.user;

import java.util.List;

import domain.card.Card;
import domain.card.CardDeck;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
	private static final int FACE_UP_INDEX = 0;
	private static final int FACE_UP_COUNT = 1;
	private static final String NAME = "딜러";

	public Dealer() {
	}

	public String getName() {
		return NAME;
	}

	public void giveCard(CardDeck cardDeck, User user) {
		user.addCard(cardDeck.draw());
	}

	public void giveCard(CardDeck cardDeck, List<Player> players) {
		players.forEach(player -> giveCard(cardDeck, player));
	}

	public List<Card> getFaceUpCards() {
		return getCards().subList(FACE_UP_INDEX, FACE_UP_COUNT);
	}
}
