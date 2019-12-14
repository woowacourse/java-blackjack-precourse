package application;

import java.util.List;
import java.util.ArrayList;

import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import inputview.InputView;
import outputview.OutputView;

public class BlackjackGame {
	private static final int NUM_INITIAL_CARDS = 2;
	private static final int BLACKJACK = 21;
	
	public void play() {
		List<String> playerNames = InputView.enterPlayerNames();
		List<Integer> bettingMoney = InputView.enterAllBettingMoney(playerNames);
		Players players = new Players(playerNames, bettingMoney);
		Dealer dealer = new Dealer();
		CardDeck cardDeck = new CardDeck();

		proceedGame(dealer, players, cardDeck);
	}
	
	private void proceedGame(Dealer dealer, Players players, CardDeck cardDeck) {
		List<WinLoseInfo> playersWinLoseInfo = new ArrayList<WinLoseInfo>();
		for (int i = 0; i < players.getSize() ; i++) {
			playersWinLoseInfo.add(WinLoseInfo.UNDETERMINED);
		}
		
		initialDrawPhase(dealer, players, cardDeck);
		checkBlackjack(dealer, players, playersWinLoseInfo);
		additionalDrawPhase(dealer, players, cardDeck);
		checkFinalWinLose(dealer, players, playersWinLoseInfo);
	}
	
	private void initialDrawPhase(Dealer dealer, Players players, CardDeck cardDeck) {
		for (int i = 0; i < NUM_INITIAL_CARDS; i++) {
			dealer.drawCard(cardDeck);
			players.drawCard(cardDeck);
		}
		OutputView.showInitialCards(dealer, players);
	}
	
	private void checkBlackjack(Dealer dealer, Players players, List<WinLoseInfo> info) {
		for (int i = 0; i < players.getSize(); i++) {
			Player player = players.getPlayerAt(i);
			if (player.calculateScore() == BLACKJACK) {
				if(dealer.calculateScore() == BLACKJACK) {
					info.set(i,WinLoseInfo.BLACKJACK);
				}
				info.set(i,WinLoseInfo.BLACKJACK);
			}
		}
	}
	
	private void additionalDrawPhase(Dealer dealer, Players players, CardDeck cardDeck) {
		
	}
	
	private void checkFinalWinLose(Dealer dealer, Players players, List<WinLoseInfo> info) {
		
	}
}
