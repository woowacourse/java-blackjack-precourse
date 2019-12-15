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
		goHitOrStand();
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
		if (dealer.isBlackJack()) {
			System.out.println("딜러가 블랙잭 입니다!!");
			return;
		}

		// 딜러 카드 한 장 오픈
		openDealerInitialCard();

		// 플레이어 카드 오픈
		openPlayersCards();

		// 플레이어 카드 더 받을지 체크
		checkPlayersWantMoreCard();

		// 딜러 카드 더 받을지 체크
		checkDealerWantMoreCard();

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

	private void checkPlayersWantMoreCard() {
		for (Player player : playerList) {
			player.checkCardNumber(cardSupplier);
		}
	}

	private void checkDealerWantMoreCard() {
		dealer.checkCardNumber(cardSupplier);
		System.out.println();
	}



}
