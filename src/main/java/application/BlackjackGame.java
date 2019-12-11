package application;

import java.util.List;
import java.util.ArrayList;

import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Player;

public class BlackjackGame {
	public void startGame() {
		CardDeck cardDeck = new CardDeck();
		Dealer dealer = new Dealer();
		List<Player> players = new ArrayList<Player>();
		
		drawStartingCard();
		drawAdditionalCard();
		checkResult();
	}
	
	public void drawStartingCard() {
		
	}
	
	public void drawAdditionalCard() {
		
	}
	
	public void checkResult() {
		
	}
}
