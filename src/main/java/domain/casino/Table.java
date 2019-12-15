package domain.casino;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.CardSupplier;
import domain.user.Dealer;
import domain.user.Player;
import domain.view.InputView;
import domain.view.OutputView;

public class Table {
	private static List<Card> cards = CardFactory.create();
	private List<Player> playerList;
	private Dealer dealer = new Dealer();
	private CardSupplier cardSupplier = new CardSupplier(cards);

	public void playBlackjack() {
		getPlayers();
		distributeCards();
	}

	// 플레이어 객체 생성 : 배팅 금액, 이름 등록
	private void getPlayers() {
		String[] playerNames = InputView.inputPlayerNames();
		playerList = new ArrayList<>(playerNames.length);
		for (int i = 0; i < playerNames.length; i++) {
			int bettigMoney;
			bettigMoney = InputView.getBettingMoney(playerNames[i]);
			Player player = new Player(playerNames[i], bettigMoney);
			playerList.add(player);
		}
	}

	// 카드 지급 : 딜러, 플레이어
	private void distributeCards() {
		for (int i = 0; i < 2; i++) {
			distributeCardsToDealer();
			distributeCardsToPlayers();
			goHitOrStand();
		}
	}

	private void distributeCardsToDealer() {
		dealer.addCard(cardSupplier.getDeal());
	}

	private void distributeCardsToPlayers() {
		for (Player player : playerList) {
			player.addCard(cardSupplier.getDeal());
		}
	}

	// 딜러 블랙잭 여부 확인 -- 딜러가 블랙잭이면 바로 최종 결과로 이동
	private void goHitOrStand() {

		// 딜러 카드 한 장 오픈
		openDealerInitialCard();

		// 플레이어 카드 오픈
		openPlayersCards();

	}

	private void openDealerInitialCard() {
		OutputView.printDealerInitialCards(dealer);
	}

	private void openPlayersCards() {
		for (Player player : playerList) {
			OutputView.printPlayerCards(player);
		}
		System.out.println();
	}

}
