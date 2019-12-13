package application;

import java.util.List;

import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Players;
import inputview.InputView;

public class BlackjackGame {
	private static final int NUM_INITIAL_CARDS = 2;
	
	public void makeNewGame() {
		List<String> playerNames = InputView.enterPlayerNames();
		List<Integer> bettingMoney = InputView.enterAllBettingMoney(playerNames);
		Players players = new Players(playerNames, bettingMoney);
		Dealer dealer = new Dealer();
		CardDeck cardDeck = new CardDeck();

		proceedGame(dealer, players, cardDeck);
	}
	
	private void proceedGame(Dealer dealer, Players players, CardDeck cardDeck) {
		PlayersWinLoseInfo info = new PlayersWinLoseInfo(players.getSize());
		
		drawFirstCards(dealer, players, cardDeck);
		showFirstCards(dealer, players);
		checkWinLose(dealer, players, info);
		drawAdditionalCards(dealer, players, cardDeck);
		checkFinalWinLose(dealer, players, info);
	}
	
	private void drawFirstCards(Dealer dealer, Players players, CardDeck cardDeck) {
		for (int i = 0; i < NUM_INITIAL_CARDS; i++) {
			dealer.drawCard(cardDeck);
			players.drawCard(cardDeck);
		}
		
		//나중에 지울 테스트 부분
		System.out.println("dealer: " + dealer.getAllCardsInString());
		System.out.println("dealer score: " + dealer.calculateScore());
		for(int i = 0; i < players.getSize(); i++) {
			System.out.println("player " + i + ": " + players.getPlayerAt(i).getAllCardsInString());
			System.out.println("player " + i + " score: " + players.getPlayerAt(i).calculateScore());
		}
	}
	
	private void showFirstCards(Dealer dealer, Players players) {
		
	}
	
	private void drawAdditionalCards(Dealer dealer, Players players, CardDeck cardDeck) {
		
	}
	
	private void checkWinLose(Dealer dealer, Players players, PlayersWinLoseInfo info) {
		
	}

	private void checkFinalWinLose(Dealer dealer, Players players, PlayersWinLoseInfo info) {
		
	}
}
