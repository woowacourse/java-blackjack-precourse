package domain.main;

import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Player;

public class Main {

	public static void main(String[] args) {
		
		InputHandler.nameHandler();
		InputHandler.bettingMoneyHandler(InputHandler.nameStringList);
		List<Player> playerList = InputHandler.makePlayer();
		
		List<Card> cardSet = CardFactory.cardShuffled();
		Card oneCard = CardFactory.pickOneCard(cardSet);
		System.out.println(oneCard.showSymbol() + oneCard.showType());
	}

}
