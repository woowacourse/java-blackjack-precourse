package services;

import controller.BlackJackController;
import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class BlackJackPlayService {
	private List<Card> cardDeck;
	private List<Player> players;
	private Dealer dealer;

	public BlackJackPlayService() {
		this.cardDeck = CardFactory.create();
		this.dealer = new Dealer();
		List<String> names = BlackJackController.getPlayersName();

	}

	public void startBlackJack() {
	}

}