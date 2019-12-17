package domain.game;

import java.util.ArrayList;

import domain.card.CardPay;
import domain.user.Dealer;
import domain.user.Player;

/**
 * Play
 * 버전 1.0
 * 게임 진행 클래스
 */
public class Play {
	private final static int DEALER = 1;
	private ArrayList<Player> playerList;
	private Dealer dealer;
	private GameRequest gameRequest;
	private CardPay cardPay;

	public Play() {
		dealer = new Dealer();
		playerList = new ArrayList<Player>();
		gameRequest = new GameRequest();
		gameRequest.requestName();
		cardPay = new CardPay();
	}

	public void requestUser() {
		playerList = gameRequest.getUserList();
	}

	public void giveCard() {
		GameUI.parsingName(playerList);
		cardPay.firstGiveUserCard(dealer);
		for (int i = 0; i < playerList.size(); i++) {
			cardPay.firstGiveUserCard(playerList.get(i));
		}
		printUserCard();
	}

	private void printUserCard() {
		dealer.printCard();
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).printCard();
		}
	}

	public void dealerAddCard() {
		if (dealer.checkDealerScore()) {
			gameRequest.requestDealerAddCard(cardPay, dealer);
		}
	}

	public void allPlayerAddCard() {
		for (int i = 0; i < playerList.size(); i++) {
			playerAddCard(playerList.get(i));
		}
	}

	private void playerAddCard(Player player) {
		while (player.checkAddCard()) {
			gameRequest.requestPlayerAddCard(cardPay, player);
		}
	}

	private int countPerson() {
		return DEALER + playerList.size();
	}

	public int checkBlackJack() {
		int blackJackNum = 0;
		if (dealer.getBlackJack()) {
			blackJackNum++;
		}
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).getBlackJack()) {
				blackJackNum++;
			}
		}
		return blackJackNum;
	}

}
