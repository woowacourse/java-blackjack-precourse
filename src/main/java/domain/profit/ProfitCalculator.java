package domain.profit;

import java.util.ArrayList;
import java.util.List;

import domain.rule.Rule;
import domain.user.Dealer;
import domain.user.Player;

public class ProfitCalculator {
	private static final double DEFAULT_PROFIT = 0;
	private static final double BLACKJACK_RATIO = 1.5;

	private final Dealer dealer;
	private final List<Player> players;

	public ProfitCalculator(Dealer dealer, List<Player> players) {
		this.dealer = dealer;
		this.players = players;
	}

	private double calculateProfit(Player player) {
		if (!Rule.isBlackjack(dealer) && Rule.isBlackjack(player)) {
			return player.getBettingMoney() * BLACKJACK_RATIO;
		}
		if (Rule.isWin(player, dealer)) {
			return player.getBettingMoney();
		}
		if (Rule.isWin(dealer, player)) {
			return -player.getBettingMoney();
		}
		return DEFAULT_PROFIT;
	}

	private Profit getDealerProfit() {
		double profit = DEFAULT_PROFIT;
		for (Player player : players) {
			profit -= calculateProfit(player);
		}
		return new Profit(dealer.getName(), profit);
	}

	private Profit getPlayerProfit(Player player) {
		double profit = calculateProfit(player);
		return new Profit(player.getName(), profit);
	}

	public List<Profit> getProfits() {
		List<Profit> profits = new ArrayList<>();
		profits.add(getDealerProfit());
		players.forEach(player -> profits.add(getPlayerProfit(player)));
		return profits;
	}
}
