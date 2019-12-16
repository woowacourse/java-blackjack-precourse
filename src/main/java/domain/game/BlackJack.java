package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import services.BlackJackInitializingService;
import services.BlackJackUIService;

import java.util.ArrayList;
import java.util.List;
public class BlackJack {
	private List<Card> cardDeck;
	private List<Player> players;
	private Dealer dealer;

	public BlackJack() {
		List<Double> bettingList = new ArrayList<Double>();
		this.cardDeck = CardFactory.create();
		this.dealer = new Dealer();
		List<String> nameList = BlackJackUIService.getPlayersName();
		for (String name : nameList) {
			bettingList.add(BlackJackUIService.getPlayersBetting(name));
		}
		this.players = BlackJackInitializingService
			.initializePlayers(nameList, bettingList);
	}


}
