package sources;

import domain.card.Card;
import utils.UtilityMethods;

import java.util.List;

public class BlackJackPlayingSource {
	public static Card popRandomCard(List<Card> cards) {
		int popIndex = UtilityMethods.generateRandomNumber(cards.size());
		return cards.remove(popIndex);
	}
}
