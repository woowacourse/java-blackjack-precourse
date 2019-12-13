package application;

import java.util.List;

import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Player;
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
	}
	
	private void showFirstCards(Dealer dealer, Players players) {
		System.out.println("딜러와 " + players.toString() + "에게 2장의 카드를 나누었습니다.");
		System.out.println("딜러: " + );
		for (int i = 0; i < players.getSize(); i++) {
			Player player = players.getPlayerAt(i);
			System.out.println(player.getName() + "카드: " + player.getAllCardsInString());
		}
	}
	
	private void drawAdditionalCards(Dealer dealer, Players players, CardDeck cardDeck) {
		
	}
	
	private void checkWinLose(Dealer dealer, Players players, PlayersWinLoseInfo info) {
		
	}

	private void checkFinalWinLose(Dealer dealer, Players players, PlayersWinLoseInfo info) {
		
	}
}
