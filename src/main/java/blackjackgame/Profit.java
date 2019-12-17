package blackjackgame;

import java.util.ArrayList;
import java.util.List;

import domain.user.*;

public class Profit {
	private static final double BLACKJACK_MULTIPLE = 1.5;
	private String name;
	private int profit = 0;
	
	Profit(String name, int profit) {
		this.name = name;
		this.profit = profit;
	}
	
	public void blackJackPrice(List<Player> players, Dealer dealer, List<String> blackJackList) {
		for (Player player : players) {
			if (blackJackList.contains(player.getName())) {
				profit += (player.getBettingMoney() * BLACKJACK_MULTIPLE);
			}
		}
	}
	
	public String toString() {
		return name + ": " + profit;
	}
}
