package view;

import java.util.ArrayList;
import java.util.List;

import domain.user.*;
import blackjackgame.Profit;

public class Output {
	private static final String HAVING_CARDS = "카드:";
	private List<Profit> profit = new ArrayList<>(); 
	
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
	
	public void blackJack(List<Player> players, Dealer dealer, List<String> blackJackList) {
		for (Player player : players) {
			printBlackJackProfit(blackJackList);
		}
	}
	
	private void printBlackJackProfit(List<String> blackJackList) {
		if (blackJackList.size() > 1 && blackJackList.contains("딜러")) {
			System.out.println(profit);
		} else if (blackJackList.size() > 1 && !blackJackList.contains("달러")) {
			System.out.println(profit);
		} else if (blackJackList.size() > 0) {
			System.out.println(profit);
		}
	}
	
}
