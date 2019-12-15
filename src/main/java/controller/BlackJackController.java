package controller;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import sources.BlackJackPlayingSource;

import java.util.List;

public class BlackJackController {
	private List<Card> cardDeck;
	private List<Player> players;
	private Dealer dealer;

	public BlackJackController(List<Card> cardDeck, List<Player> players) {
		this.cardDeck = cardDeck;
		this.players = players;
		this.dealer = new Dealer();
	}

	private void drawFromCardDeck() {
		for ( Player player : this.players ) {
			player.addCard(BlackJackPlayingSource.popRandomCard(this.cardDeck));
		}
		dealer.addCard(BlackJackPlayingSource.popRandomCard(this.cardDeck));
	}
}
