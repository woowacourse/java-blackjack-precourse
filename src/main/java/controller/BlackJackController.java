package controller;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import services.BlackJackInputService;
import utils.UtilityMethods;

import java.util.List;

public class BlackJackController {
	private List<Card> cardDeck;
	private List<Player> players;
	private Dealer dealer;

	public BlackJackController() {
		this.cardDeck = CardFactory.create();
		this.dealer = new Dealer();
		List<String> names = BlackJackInputService.getPlayersName();
	}

	public void startBlackJack() {}

	private void drawFromCardDeck() {
		for ( Player player : this.players ) {
			player.addCard(popRandomCard(this.cardDeck));
		}
		dealer.addCard(popRandomCard(this.cardDeck));
	}

	public Card popRandomCard(List<Card> cards) {
		int popIndex = UtilityMethods.generateRandomNumber(cards.size());
		return cards.remove(popIndex);
	}
}
