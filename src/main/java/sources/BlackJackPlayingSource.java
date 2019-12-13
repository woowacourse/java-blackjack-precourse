package sources;

import domain.card.Card;
import domain.card.CardFactory;
import utils.UtilityMethods;

import java.util.List;

public class BlackJackPlayingSource {
	public void startBlackJack(){
		List<Card> cardDeck = CardFactory.create();
	}

	private Card popRandomCard(List<Card> cards){
		int popIndex = UtilityMethods.generateRandomNumber(cards.size());
		return cards.remove(popIndex);
	}
}
