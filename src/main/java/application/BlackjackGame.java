package application;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import inputview.InputView;
import outputview.OutputView;

public class BlackjackGame {
	private static final int NUM_INITIAL_CARDS = 2;
	private static final int BLACKJACK = 21;
	private static final int DEALER_THRESHOLD = 16;
	
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
		playerAdditionalDrawPhase(players, cardDeck, playersWinLoseInfo);
		dealerAdditionalDrawPhase(dealer, cardDeck);
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
		List<Integer> indexOfBlackjack = findBlackjackIndex(players);
		updateBlackjackInfo(dealer, indexOfBlackjack, info);
		OutputView.showInitialResult(players, info, indexOfBlackjack);
	}
	
	private List<Integer> findBlackjackIndex(Players players) {
		return players.getPlayers().stream()
				.filter(player -> (player.calculateScore() == BLACKJACK))
				.map(player -> players.getPlayers().indexOf(player))
				.collect(Collectors.toList());
	}
	
	private void updateBlackjackInfo(Dealer dealer, List<Integer> BlackjackIndex, List<WinLoseInfo> info) {
		if (dealer.calculateScore() == BLACKJACK) {
			BlackjackIndex.stream().forEach(index -> info.set(index, WinLoseInfo.DRAW));
		}
		BlackjackIndex.stream().forEach(index -> info.set(index, WinLoseInfo.BLACKJACK));
	}
	
	private void playerAdditionalDrawPhase(Players players, CardDeck cardDeck, List<WinLoseInfo> info) {
		List<Integer> indexOfNotBlackjack = findNotBlackjackIndex(players);
		indexOfNotBlackjack.stream()
				.forEach(index -> drawAdditionalCards(players.getPlayerAt(index), cardDeck));
		updateLoser(players, info);
	}
	
	private List<Integer> findNotBlackjackIndex(Players players) {
		return players.getPlayers().stream()
				.filter(player -> (player.calculateScore() != BLACKJACK))
				.map(player -> players.getPlayers().indexOf(player))
				.collect(Collectors.toList());
	}
	
	private void drawAdditionalCards(Player player, CardDeck cardDeck) {
		try {
			drawUntilDontWant(player, cardDeck);
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		}
		OutputView.showBlankLine();
	}
	
	private void drawUntilDontWant(Player player, CardDeck cardDeck) {
		while(InputView.enterIfDrawAdditionalCard(player)) {
			player.drawCard(cardDeck);
			OutputView.showPlayerCards(player);
			checkOverBlackJack(player);
		}
	}
	
	private void updateLoser(Players players, List<WinLoseInfo> info) {
		players.getPlayers().stream()
				.filter(player -> (player.calculateScore() > BLACKJACK))
				.map(player -> players.getPlayers().indexOf(player))
				.forEach(index -> info.set(index, WinLoseInfo.LOSE));
	}
	
	private static void checkOverBlackJack(Player player) {
		if (player.calculateScore() > BLACKJACK) {
			throw new IllegalStateException("점수합이 21을 초과했습니다." + player.getName() + "는 패배했습니다.");
		}
	}
	
	private void dealerAdditionalDrawPhase(Dealer dealer, CardDeck cardDeck) {
		try {
			checkOverThreshold(dealer);
			dealer.drawCard(cardDeck);
			OutputView.showDealerDrawsCard();
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		}
		OutputView.showBlankLine();
	}
	
	private static void checkOverThreshold(Dealer dealer) {
		if (dealer.calculateScore() > DEALER_THRESHOLD) {
			throw new IllegalStateException("딜러의 점수합이 17이상입니다. 카드를 더 뽑지 않습니다.");
		}
	}
	
	private void checkFinalWinLose(Dealer dealer, Players players, List<WinLoseInfo> info) {
		updateFinalWinLoseInfo(dealer, players, info);
		OutputView.showAllFinalResults(dealer, players, info);
	}
	
	private void updateFinalWinLoseInfo(Dealer dealer, Players players, List<WinLoseInfo> info) {
		List<Integer> indexOfUndetermined = findUndeterminedIndex(players);
		indexOfUndetermined.stream()
				.forEach(index -> info.set(index, decideResult(dealer, players.getPlayerAt(index))));
		if (dealer.calculateScore() > BLACKJACK) {
			indexOfUndetermined.stream()
					.forEach(index -> info.set(index, WinLoseInfo.WIN));
		}
	}
	
	private List<Integer> findUndeterminedIndex(Players players) {
		return players.getPlayers().stream()
				.filter(player -> player.calculateScore() < BLACKJACK)
				.map(player -> players.getPlayers().indexOf(player))
				.collect(Collectors.toList());
	}
	
	private WinLoseInfo decideResult(Dealer dealer, Player player) {
		if (dealer.calculateScore() == player.calculateScore() ) {
			return WinLoseInfo.DRAW;
		}
		return decideWinOrLose(dealer, player);
	}
	
	private WinLoseInfo decideWinOrLose(Dealer dealer, Player player) {
		if (dealer.calculateScore() > player.calculateScore() 
				|| player.calculateScore() > BLACKJACK) {
			return WinLoseInfo.LOSE;
		}
		return WinLoseInfo.WIN;
	}
}
