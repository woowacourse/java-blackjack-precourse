package view;

import java.util.ArrayList;
import java.util.List;

import domain.user.*;

public class Output {
	private static final String HAVING_CARDS = "카드:";
	
	public void printDrawCards(Dealer dealer, List<Player> players, String[] playerNames) {
		System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장의 카드를 나누었습니다.");
		List<String> playerCards = new ArrayList<>();
		System.out.println("딜러" + HAVING_CARDS + dealer.getDealerSymbolAndType());
		for (Player player : players) {
			playerCards.clear();
			playerCards = player.getPlayerSymbolAndType();
			System.out.println(player.getName() + HAVING_CARDS + String.join(",", playerCards));
		}
	}
}
