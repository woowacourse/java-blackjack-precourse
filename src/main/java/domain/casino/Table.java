package domain.casino;

import java.util.ArrayList;
import java.util.List;

import domain.user.Player;
import domain.view.InputView;

public class Table {
	private List<Player> playerList;

	public void playBlackjack() {
		getPlayers();
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

}
