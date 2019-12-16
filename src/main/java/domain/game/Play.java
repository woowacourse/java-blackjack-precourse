package domain.game;

import java.util.ArrayList;

import domain.user.Dealer;
import domain.user.Player;

/**
 * 게임 전반적 진행 클래스
 */
public class Play {
	ArrayList playerList;
	Dealer dealer;
	GameRequest gameRequest;
	public Play() {
		dealer = new Dealer();
		playerList = new ArrayList<Player>();
		gameRequest = new GameRequest();
		gameRequest.requestName();
	}

	public void requestUser(){
		playerList = gameRequest.getUserList();
	}
}
