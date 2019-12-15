package domain.casino;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.CardSupplier;
import domain.user.Dealer;
import domain.user.Player;
import domain.view.InputView;

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

}
