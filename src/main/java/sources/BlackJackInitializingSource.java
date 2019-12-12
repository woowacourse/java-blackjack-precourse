package sources;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Player;


/**
 * 플레이어, 딜러, 카드 초기화 클래스
 */
public class BlackJackInitializingSource {
	public List<Player> initializePlayers(List<String> names, List<Double> bettings) {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < names.size(); i++) {
			players.add(new Player(names.get(i), bettings.get(i)));
		}
		return players;
	}

	public List<Card> initializeCardDeck() {
		List<Card> deck = CardFactory.create();
		return deck;
	}
}
