package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import services.BlackJackUIService;

import java.util.List;
public class BlackJack {
	private List<Card> cardDeck;
	private List<Player> players;
	private Dealer dealer;

	public BlackJack() {
		this.cardDeck = CardFactory.create();
		this.dealer = new Dealer();
		List<String> names = BlackJackUIService.getPlayersName();

	}
}
