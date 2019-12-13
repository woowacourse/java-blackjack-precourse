package sources;

import domain.card.Card;
import domain.user.Player;
import utils.UtilityMethods;

import java.util.List;

public class BlackJackPlayingSource {
	public void startRound(List<Player> players, List<Card> cardDeck) {

	}

	private Card popRandomCard(List<Card> cards) {
		int popIndex = UtilityMethods.generateRandomNumber(cards.size());
		return cards.remove(popIndex);
	}
}
