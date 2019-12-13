package sources;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.List;

public class BlackJackPlayingSource {
	public void startBlackJack(){
		List<Card> cardDeck = CardFactory.create();
	}

	private Card popRandomCard(List<Card> cards){
		int popIndex = generateRandomNumber(cards.size());
		return cards.remove(popIndex);
	}

	private int generateRandomNumber(int limit){
		int randomNumber = (int) (Math.random()*limit);
		return  randomNumber;
	}
}
