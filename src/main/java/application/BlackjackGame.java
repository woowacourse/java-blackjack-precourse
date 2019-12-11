package application;

import java.util.List;

import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Players;
import inputview.InputView;

public class BlackjackGame {
	public void startGame() {
		List<String> playerNames = InputView.enterPlayerNames();
		List<Integer> bettingMoney = InputView.enterAllBettingMoney(playerNames);
		Players players = new Players(playerNames, bettingMoney);
		CardDeck cardDeck = new CardDeck();
		Dealer dealer = new Dealer();
		
		doGame();
		checkResult();
	}
	
	public void doGame() {

	}
	
	public void checkResult() {
		
	}
}
