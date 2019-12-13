package application;

import java.util.List;

import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Players;
import inputview.InputView;

public class BlackjackGame {
	public void makeNewGame() {
		List<String> playerNames = InputView.enterPlayerNames();
		List<Integer> bettingMoney = InputView.enterAllBettingMoney(playerNames);
		Players players = new Players(playerNames, bettingMoney);
		CardDeck cardDeck = new CardDeck();
		Dealer dealer = new Dealer();
		
		proceedGame(dealer, players, cardDeck);
	}
	
	private void proceedGame(Dealer dealer, Players players, CardDeck cardDeck) {
		PlayersWinLoseInfo info = new PlayersWinLoseInfo(players.getSize());
		
		drawFirstCards(dealer, players, cardDeck);
		checkWinLose(dealer, players, info);
		drawAdditionalCards(dealer, players, cardDeck);
		checkFinalWinLose(dealer, players, info);
	}
	
	private void drawFirstCards(Dealer dealer, Players players, CardDeck cardDeck) {
		
	}
	
	private void drawAdditionalCards(Dealer dealer, Players players, CardDeck cardDeck) {
		
	}
	
	private void checkWinLose(Dealer dealer, Players players, PlayersWinLoseInfo info) {
		
	}

	private void checkFinalWinLose(Dealer dealer, Players players, PlayersWinLoseInfo info) {
		
	}
}
