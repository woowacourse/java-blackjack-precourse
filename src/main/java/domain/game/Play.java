package domain.game;

import java.util.ArrayList;

import domain.card.CardPay;
import domain.user.Dealer;
import domain.user.Player;

/**
 * 게임 전반적 진행 클래스
 */
public class Play {
	ArrayList<Player> playerList;
	Dealer dealer;
	GameRequest gameRequest;
	CardPay cardPay;

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
		cardPay.giveUserCard(dealer);
	}
}
